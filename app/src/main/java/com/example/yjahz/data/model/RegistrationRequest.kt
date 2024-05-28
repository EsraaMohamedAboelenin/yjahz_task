package com.example.yjahz.data.model

import com.google.gson.annotations.SerializedName

data class RegistrationRequest(@field:SerializedName("name") val name: String,
                               @field:SerializedName("email") val email: String,
                               @field:SerializedName("password") val password: String,
                               @field:SerializedName("phone")val phone:String,
                        )
