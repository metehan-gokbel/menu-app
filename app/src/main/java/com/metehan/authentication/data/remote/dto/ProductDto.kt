package com.metehan.authentication.data.remote.dto


import com.google.gson.annotations.SerializedName
import com.metehan.authentication.domain.models.Product
import com.metehan.authentication.domain.models.ProductCategoryEntry

data class ProductDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("price")
    val price: String,
    @SerializedName("productCategory")
    val productCategory: ProductCategory,
    @SerializedName("productName")
    val productName: String
)

fun List<ProductDto>.toProductList(): List<Product> {
    return map { dto ->
        val productCategoryEntry = ProductCategoryEntry(dto.productCategory.id, dto.productCategory.productCategoryName)
        Product(dto.id, dto.price, productCategoryEntry, dto.productName)
    }
}