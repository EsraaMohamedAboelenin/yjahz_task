package com.example.yjahz.data.remote

import com.example.yjahz.data.model.LoginRequest
import com.example.yjahz.data.model.RegistrationRequest
import javax.inject.Inject

class ApiHelper @Inject constructor (private val apiService: ApiService) {

    suspend fun register(customerRegistrationRequest: RegistrationRequest) = apiService.register(customerRegistrationRequest)
    suspend fun login(loginRequest: LoginRequest) = apiService.login(loginRequest)
    suspend fun getCategories(token:String) = apiService.getCategories(token)
    suspend fun getPopularProduct(token: String) = apiService.getPopularProduct(token)
    suspend fun getTrendingProduct(token: String) = apiService.getTrendingProduct(token)
}