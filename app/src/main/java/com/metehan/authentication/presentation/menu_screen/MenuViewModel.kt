package com.metehan.authentication.presentation.menu_screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.metehan.authentication.domain.models.Merchant
import com.metehan.authentication.domain.use_case.get_merchant.GetMerchantUseCase
import com.metehan.authentication.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(
    private val useCase: GetMerchantUseCase
) : ViewModel(){

    var merchantList = mutableStateOf<List<Merchant>>(listOf())
    var loadError = mutableStateOf("")
    var isLoading = mutableStateOf(false)

    private var cachedMerchantList = listOf<Merchant>()
    private var isSearchStarting = true
    var isSearching = mutableStateOf(false)

    init {
        loadMerchant()
    }

    fun searchMenuList(query: String){
        val listToSearch = if(isSearchStarting){
            merchantList.value
        }else{
            cachedMerchantList
        }
        viewModelScope.launch(Dispatchers.Default) {
            if(query.isEmpty()){
                merchantList.value = cachedMerchantList
                isSearching.value = false
                isSearchStarting = true
                return@launch
            }
            val results = listToSearch.filter {
                // Search by merchant name
                it.merchantName.contains(query.trim(), ignoreCase = true)
            }
            if(isSearchStarting){
                cachedMerchantList = merchantList.value
                isSearchStarting = false
            }
            merchantList.value = results
            isSearching.value = true
        }
    }

    fun loadMerchant() {
        useCase.executeMerchant().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    val newMerchants = result.data?.map { merchant ->
                        Merchant(
                            merchant.id,
                            merchant.latitude,
                            merchant.longitude,
                            merchant.merchantName,
                            merchant.merchantCategory
                        )
                    } ?: emptyList()
                    val distinctNewMerchants = newMerchants.filter { newMerchant ->
                        !merchantList.value.any { it.id == newMerchant.id }
                    }

                    loadError.value = ""
                    isLoading.value = false
                    merchantList.value += distinctNewMerchants
                }
                is Resource.Error -> {
                    loadError.value = result.message ?: "Unknown error"
                    isLoading.value = false
                }
                is Resource.Loading -> {
                    isLoading.value = true
                }
            }
        }.launchIn(viewModelScope)
    }
}