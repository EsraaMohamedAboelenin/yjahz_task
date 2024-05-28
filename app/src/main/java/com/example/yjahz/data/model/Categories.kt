package com.example.yjahz.data.model

import com.google.gson.annotations.SerializedName

data class Categories(@SerializedName("message")val message:String,
                      @SerializedName("data")val data:Data)
{
    data class Data(@SerializedName("data") val categories:ArrayList<Category>

    ){
        data class Category(@SerializedName("name")val categoryName:String?,
        @SerializedName("image")val categoryImage:String)
    }
}
