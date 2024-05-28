package com.example.yjahz.ui.auth.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agel.common.utils.Resource
import com.example.yjahz.common.utils.Constants
import com.example.yjahz.common.utils.Validation
import com.example.yjahz.data.model.AuthResponse
import com.example.yjahz.data.model.LoginRequest
import com.example.yjahz.data.model.User
import com.example.yjahz.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val authRepository: AuthRepository) : ViewModel(){

    private var _isEmailValid= MutableLiveData(true)
    val isEmailValid: LiveData<Boolean> get() = _isEmailValid

    private var _isPasswordValid= MutableLiveData(true)
    val isPasswordValid: LiveData<Boolean> get() = _isPasswordValid


    private var _isValid= MutableLiveData(true)
    val isValid: LiveData<Boolean> get() = _isValid



    private fun checkValidation(loinRequest: LoginRequest) : Boolean {
        if(Validation.validatePassword(loinRequest.password))
            _isPasswordValid.postValue(true)
        else _isPasswordValid.postValue(false)
        if(Validation.validateEmail(loinRequest.email))
            _isEmailValid.postValue(true)
        else _isEmailValid.postValue(false)

        return if(
            Validation.validatePassword(loinRequest.password)&&
            Validation.validateEmail(loinRequest.email)) {
            setValidation(true)
            true
        } else {
            setValidation(false)
            false
        }
    }
    private fun setValidation(isValid:Boolean){
        _isValid.postValue(isValid)
    }

    fun login(loginRequest: LoginRequest) : LiveData<Resource<AuthResponse>> {
        lateinit var response : Response<AuthResponse>
        val loginResponseLiveData = MutableLiveData<Resource<AuthResponse>>()
        if(checkValidation(loginRequest))
        {
            setValidation(true)
            viewModelScope.launch(Dispatchers.IO) {
                try {
                    response = authRepository.login(loginRequest)
                    if (response.isSuccessful) {
                        response.body()?.let {
                            val token = Constants.Keys.BERER + it.data?.token
                            val userName = it.data?.name
                            authRepository.saveUser(User(token, userName))
                            Log.d("login", it.toString())
                           loginResponseLiveData.postValue(Resource.success(it))
                        }
                    } else {
                        val errorMsg = response.errorBody()?.string()?.let {
                            JSONObject(it).getString("message")
                        }
                        Log.d("error", errorMsg.toString())
                       loginResponseLiveData.postValue(
                            Resource.error(
                                data = null,
                                message = errorMsg.toString()
                            )
                        )

                    }
                } catch (e: Exception) {
                    loginResponseLiveData.postValue(
                        Resource.error(
                            data = null,
                            message = "حاول مرة اخري"
                        )
                    )
                    Log.d("login_error", e.toString())
                }
            }
        }
        else if(!checkValidation(loginRequest)) {
           loginResponseLiveData.postValue(Resource.error(data = null, message = "" ))
        }


        return loginResponseLiveData
    }
}