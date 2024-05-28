package com.example.yjahz.data.remote

import com.example.yjahz.data.model.RegistrationRequest
import com.example.yjahz.data.model.AuthResponse
import com.example.yjahz.data.model.Categories
import com.example.yjahz.data.model.LoginRequest
import com.example.yjahz.data.model.Popular
import com.example.yjahz.data.model.TrendingProduct
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST


interface ApiService {
    @POST("api/client-register")
    suspend fun register(@Body customerRegistrationRequest: RegistrationRequest): Response<AuthResponse>

    @POST("api/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<AuthResponse>
    @GET("api/home-base-categories")
    suspend fun getCategories(@Header("authorization")token:String):Response<Categories>
    @GET("api/popular-sellers")
    suspend fun getPopularProduct(@Header("authorization")token:String):Response<Popular>
    @GET("api/trending-sellers")
    suspend fun getTrendingProduct(@Header("authorization")token:String):Response<TrendingProduct>
}