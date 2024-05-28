package com.example.yjahz.data.model

import com.google.gson.annotations.SerializedName

data class Popular(@SerializedName("message")val message:String,
                   @SerializedName("data")val data:Data)
{
    data class Data(@SerializedName("data") val popularList:ArrayList<Item>

    ){
        data class Item(@SerializedName("name")val name:String?,
                            @SerializedName("image")val image:String,
        @SerializedName("distance")val distance:String,
        @SerializedName("rate")val rate:String,
        @SerializedName("is_favorite")val isFavorite:Boolean)
    }
}
