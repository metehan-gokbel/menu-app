package com.metehan.authentication.data.remote.dto


import com.google.gson.annotations.SerializedName

data class MerchantCategory(
    @SerializedName("id")
    val id: String,
    @SerializedName("merchantCategoryName")
    val merchantCategoryName: String
)