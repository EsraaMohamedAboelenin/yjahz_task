package com.example.yjahz.data.model

import com.google.gson.annotations.SerializedName

data class TrendingProduct(@SerializedName("message")val message:String,
@SerializedName("data")val data:Data)
{
    data class Data(@SerializedName("data") val tendingList:ArrayList<Item>

    ){
        data class Item(
                            @SerializedName("image")val image:String)
    }
}
