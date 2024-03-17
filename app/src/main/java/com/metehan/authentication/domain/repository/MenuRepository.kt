package com.metehan.authentication.domain.repository

import com.metehan.authentication.data.remote.dto.MerchantDto
import com.metehan.authentication.data.remote.dto.ProductDto

interface MenuRepository {
    suspend fun getMerchantList() : List<MerchantDto>
    suspend fun getProduct(merchantId: String): List<ProductDto>
}