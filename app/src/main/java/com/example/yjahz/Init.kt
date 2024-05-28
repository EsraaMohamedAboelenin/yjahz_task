package com.example.yjahz

import android.app.Application

import dagger.hilt.android.HiltAndroidApp
@HiltAndroidApp
class Init : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}
