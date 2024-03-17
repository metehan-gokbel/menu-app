package com.metehan.authentication.data.remote.dto


import com.google.gson.annotations.SerializedName
import com.metehan.authentication.domain.models.Merchant
import com.metehan.authentication.domain.models.MerchantCategoryEntry

data class MerchantDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("latitude")
    val latitude: Double,
    @SerializedName("longtitude")
    val longitude: Double,
    @SerializedName("merchantCategory")
    val merchantCategory: List<MerchantCategory>,
    @SerializedName("merchantName")
    val merchantName: String
)

fun List<MerchantDto>.toMerchantList(): List<Merchant> {
    return map { dto ->
        val merchantCategoryEntries = dto.merchantCategory.map { category ->
            MerchantCategoryEntry(category.id, category.merchantCategoryName)
        }
        Merchant(dto.id, dto.latitude, dto.longitude, dto.merchantName, merchantCategoryEntries)
    }
}