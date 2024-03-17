package com.metehan.authentication.domain.use_case.get_product

import com.metehan.authentication.data.remote.dto.ProductDto
import com.metehan.authentication.data.remote.dto.toMerchantList
import com.metehan.authentication.data.remote.dto.toProductList
import com.metehan.authentication.domain.models.Product
import com.metehan.authentication.domain.repository.MenuRepository
import com.metehan.authentication.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetProductUseCase @Inject constructor(private val repository: MenuRepository){
    fun executeProduct(merchantId: String): Flow<Resource<List<Product>>> = flow{
        try {
            emit(Resource.Loading())
            val productDtoList = repository.getProduct(merchantId)
            emit(Resource.Success(productDtoList.toProductList()))
        }catch (e: Exception){
            emit(Resource.Error(message = "An error occurred."))
        }
    }
}