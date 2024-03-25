package com.metehan.authentication.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
data class Merchant(
    val id: String,
    val latitude: Double,
    val longitude: Double,
    val merchantName: String,
    val merchantCategory: List<MerchantCategoryEntry>,
) : Parcelable

@Parcelize
data class MerchantCategoryEntry(
    val id: String,
    val merchantCategoryName: String
): Parcelable

@Parcelize
data class MerchantList(val merchants: List<Merchant>?) : Parcelable