package com.metehan.authentication.data.repository

import com.metehan.authentication.data.remote.MenuAPI
import com.metehan.authentication.data.remote.dto.MerchantDto
import com.metehan.authentication.data.remote.dto.ProductDto
import com.metehan.authentication.domain.repository.MenuRepository
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class MenuRepositoryImpl @Inject constructor(private val api: MenuAPI): MenuRepository {
    override suspend fun getMerchantList(): List<MerchantDto> {
        return api.getMerchantList()
    }

    override suspend fun getProduct(merchantId: String): List<ProductDto> {
        return api.getProduct(merchantId)
    }
}