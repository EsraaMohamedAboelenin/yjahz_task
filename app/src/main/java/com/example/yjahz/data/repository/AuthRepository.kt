package com.example.yjahz.data.repository

import com.example.yiahz.data.local.preferences.PreferencesHelper
import com.example.yjahz.data.model.LoginRequest
import com.example.yjahz.data.model.RegistrationRequest
import com.example.yjahz.data.model.User
import com.example.yjahz.data.remote.ApiHelper
import javax.inject.Inject

class AuthRepository @Inject constructor (private val preferencesHelper: PreferencesHelper, private val apiHelper: ApiHelper) {
    fun saveUser(user : User){
        preferencesHelper.saveUser(user) }
    fun getUser() : User =preferencesHelper.getUser()
    suspend fun register(customerRegistrationRequest: RegistrationRequest) = apiHelper.register(customerRegistrationRequest)
    suspend fun login(loginRequest: LoginRequest) = apiHelper.login(loginRequest)
}