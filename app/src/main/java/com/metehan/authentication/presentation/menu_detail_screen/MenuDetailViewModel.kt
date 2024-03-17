package com.metehan.authentication.presentation.menu_detail_screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.metehan.authentication.data.remote.dto.ProductDto
import com.metehan.authentication.domain.models.Merchant
import com.metehan.authentication.domain.models.Product
import com.metehan.authentication.domain.use_case.get_product.GetProductUseCase
import com.metehan.authentication.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MenuDetailViewModel @Inject constructor(private val useCase: GetProductUseCase): ViewModel(){

    var productList = mutableStateOf<List<Product>>(listOf())
    var loadError = mutableStateOf("")
    var isLoading = mutableStateOf(false)

    private var cachedProductList = listOf<Product>()
    private var isSearchStarting = true
    var isSearching = mutableStateOf(false)

    fun searchMenuDetailList(query: String){
        val listToSearch = if(isSearchStarting){
            productList.value
        }else{
            cachedProductList
        }
        viewModelScope.launch(Dispatchers.Default) {
            if(query.isEmpty()){
                productList.value = cachedProductList
                isSearching.value = false
                isSearchStarting = true
                return@launch
            }
            val results = listToSearch.filter {
                // Search by merchant name
                it.productName.contains(query.trim(), ignoreCase = true)
            }
            if(isSearchStarting){
                cachedProductList = productList.value
                isSearchStarting = false
            }
            productList.value = results
            isSearching.value = true
        }
    }

    fun loadProduct(merchantId: String) {
        useCase.executeProduct(merchantId).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    val newProducts = result.data?.map { product ->
                        Product(
                            product.id,
                            product.price,
                            product.productCategory,
                            product.productName
                        )
                    } ?: emptyList()
                    val distinctNewProducts = newProducts.filter { newProduct ->
                        !productList.value.any { it.id == newProduct.id }
                    }

                    loadError.value = ""
                    isLoading.value = false
                    productList.value += distinctNewProducts
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