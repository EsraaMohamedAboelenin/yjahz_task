package com.example.yjahz.data.model

import com.google.gson.annotations.SerializedName

data class AuthResponse(
@SerializedName("message")val message:String,
@SerializedName("data")val data:Data)
{
    data class Data(@SerializedName("token") val token:String,
                    @SerializedName("name")val name:String
                   )
}
