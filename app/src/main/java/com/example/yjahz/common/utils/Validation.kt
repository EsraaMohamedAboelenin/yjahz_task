package com.example.yjahz.common.utils

import android.util.Patterns

object Validation {
    fun validatePassword(password : String): Boolean = (password.length >=8)
    fun validatePhone(phoneNumber : String): Boolean = (phoneNumber.length == 11)&&phoneNumber[0].toString() == "0" && phoneNumber[1].toString() == "1"
    fun validateText(text : String): Boolean = (text.isNotEmpty())
    fun confirmPassword(password : String,confirmPassword:String): Boolean = password == confirmPassword
    fun validateUserName(userName : String): Boolean = (userName.isNotEmpty())
    fun validateEmail(email:String):Boolean = email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
}