package com.example.yjahz.data.repository

import com.example.yiahz.data.local.preferences.PreferencesHelper
import com.example.yjahz.data.remote.ApiHelper
import javax.inject.Inject

class HomeRepository  @Inject constructor (private val preferencesHelper: PreferencesHelper, private val apiHelper: ApiHelper){
    suspend fun getCategories() = apiHelper.getCategories(preferencesHelper.getUser().token)
    suspend fun getPopularProduct() = apiHelper.getPopularProduct(preferencesHelper.getUser().token)

    suspend fun getTrendingProduct() = apiHelper.getTrendingProduct(preferencesHelper.getUser().token)
}