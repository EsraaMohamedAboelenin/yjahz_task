package com.example.yjahz.ui.auth.registration

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agel.common.utils.Resource
import com.example.yjahz.common.utils.Constants
import com.example.yjahz.common.utils.Validation
import com.example.yjahz.data.model.RegistrationRequest
import com.example.yjahz.data.model.AuthResponse
import com.example.yjahz.data.model.User
import com.example.yjahz.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject
@HiltViewModel
class RegistrationViewModel @Inject constructor(private val authRepository: AuthRepository) : ViewModel(){
    private var _isPhoneValid= MutableLiveData(true)
    val isPhoneValid: LiveData<Boolean> get() = _isPhoneValid

    private var _isPasswordValid= MutableLiveData(true)
    val isPasswordValid: LiveData<Boolean> get() = _isPasswordValid

    private var _isPasswordConfirmed= MutableLiveData(true)
    val isPasswordConfirmed: LiveData<Boolean> get() = _isPasswordConfirmed

    private var _isUserNameValid= MutableLiveData(true)
    val isUserNameValid: LiveData<Boolean> get() = _isUserNameValid

    private var _isEmailValid= MutableLiveData(true)
    val isEmailValid: LiveData<Boolean> get() = _isEmailValid

    private var _isValid= MutableLiveData(true)
    val isValid: LiveData<Boolean> get() = _isValid


   private fun checkValidation(customerRegistration:RegistrationRequest,confirmPassword:String) : Boolean {
        if(Validation.validatePassword(customerRegistration.password))
            _isPasswordValid.postValue(true)
        else _isPasswordValid.postValue(false)
        if( Validation. confirmPassword(customerRegistration.password,confirmPassword))
            _isPasswordConfirmed.postValue(true)
        else _isPasswordConfirmed.postValue(false)

       if(Validation.validateUserName(customerRegistration.name))
           _isUserNameValid.postValue(true)
       else _isUserNameValid.postValue(false)



        if(Validation.validateEmail(customerRegistration.email))
            _isEmailValid.postValue(true)
        else _isEmailValid.postValue(false)

        if(Validation.validatePhone(customerRegistration.phone))
            _isPhoneValid.postValue(true)
        else _isPhoneValid.postValue(false)


        return if(Validation.validateUserName(customerRegistration.name)&&
            Validation.validatePassword(customerRegistration.password)&&
            Validation.validateEmail(customerRegistration.email)&&
            Validation. confirmPassword(customerRegistration.password,confirmPassword)&&
            Validation.validatePhone(customerRegistration.phone)) {
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
    fun register(registrationRequest: RegistrationRequest,confirmPassword: String) : LiveData<Resource<AuthResponse>> {
        lateinit var response : Response<AuthResponse>
        val customerRegistrationLiveData = MutableLiveData<Resource<AuthResponse>>()
        if(checkValidation(registrationRequest,confirmPassword))
            {
                setValidation(true)
                viewModelScope.launch(Dispatchers.IO) {
                    try {
                        response = authRepository.register(registrationRequest)
                        if (response.isSuccessful) {
                            response.body()?.let {
                                val token = Constants.Keys.BERER + it.data?.token
                                val userName = it.data?.name
                                authRepository.saveUser(User(token, userName))
                                Log.d("register", it.toString())
                                customerRegistrationLiveData.postValue(Resource.success(it))
                            }
                        } else {
                            val errorMsg = response.errorBody()?.string()?.let {
                                JSONObject(it).getString("message")
                            }
                            Log.d("error", errorMsg.toString())
                            customerRegistrationLiveData.postValue(
                                Resource.error(
                                    data = null,
                                    message = errorMsg.toString()
                                )
                            )

                        }
                    } catch (e: Exception) {
                        customerRegistrationLiveData.postValue(
                            Resource.error(
                                data = null,
                                message = "حاول مرة اخري"
                            )
                        )
                        Log.d("registration_error", e.toString())
                    }
                }
            }
        else if(!checkValidation(registrationRequest, confirmPassword)) {
            customerRegistrationLiveData.postValue(Resource.error(data = null, message = "" ))
        }


        return customerRegistrationLiveData
    }
}