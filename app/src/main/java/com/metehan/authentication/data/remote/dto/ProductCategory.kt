package com.metehan.authentication.data.remote.dto


import com.google.gson.annotations.SerializedName

data class ProductCategory(
    @SerializedName("id")
    val id: String,
    @SerializedName("productCategoryName")
    val productCategoryName: String
)