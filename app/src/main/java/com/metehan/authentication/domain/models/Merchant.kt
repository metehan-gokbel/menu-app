package com.metehan.authentication.domain.models

data class Merchant(
    val id: String,
    val latitude: Double,
    val longitude: Double,
    val merchantName: String,
    val merchantCategory: List<MerchantCategoryEntry>,
)

data class MerchantCategoryEntry(
    val id: String,
    val merchantCategoryName: String
)