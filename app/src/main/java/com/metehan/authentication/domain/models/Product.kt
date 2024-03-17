package com.metehan.authentication.domain.models


data class Product(
    val id: String,
    val price: String,
    val productCategory: ProductCategoryEntry,
    val productName: String
)

data class ProductCategoryEntry(
    val id: String,
    val productCategoryName: String
)