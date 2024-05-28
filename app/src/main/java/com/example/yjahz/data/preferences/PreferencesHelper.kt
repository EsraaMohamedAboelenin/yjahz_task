package com.example.yiahz.data.local.preferences

import android.content.Context
import androidx.preference.PreferenceManager
import com.example.yjahz.common.utils.Constants
import com.example.yjahz.data.model.User

import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton
@Singleton
class PreferencesHelper @Inject constructor( @ApplicationContext context: Context?) {
    private val appContext = context?.applicationContext
    fun saveUser(user: User){
        val gson = Gson()
        val json = gson.toJson(user)
        PreferenceManager.getDefaultSharedPreferences(appContext).edit().putString(Constants.Preferences.USER, json).apply()
    }

    fun getUser(): User {
        val gson = Gson()
        val json: String? = PreferenceManager.getDefaultSharedPreferences(appContext)
            .getString(Constants.Preferences.USER, "")
        return if(json == ""){
            User("","")
        }
        else
            gson.fromJson(json, User::class.java)
    }
}






