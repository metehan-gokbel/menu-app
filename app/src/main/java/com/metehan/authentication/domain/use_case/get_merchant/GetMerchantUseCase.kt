package com.metehan.authentication.domain.use_case.get_merchant

import com.metehan.authentication.data.remote.dto.toMerchantList
import com.metehan.authentication.domain.models.Merchant
import com.metehan.authentication.domain.repository.MenuRepository
import com.metehan.authentication.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMerchantUseCase @Inject constructor(private val repository: MenuRepository) {
    fun executeMerchant(): Flow<Resource<List<Merchant>>> = flow{
        try {
            emit(Resource.Loading())
            val merchantDtoList = repository.getMerchantList()
            emit(Resource.Success(merchantDtoList.toMerchantList()))
        }catch (e: Exception){
            emit(Resource.Error(message = "An error occurred."))
        }
    }
}