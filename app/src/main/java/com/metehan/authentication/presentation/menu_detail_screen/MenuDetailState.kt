package com.metehan.authentication.presentation.menu_detail_screen

import com.metehan.authentication.data.remote.dto.ProductDto

data class MenuDetailState(
    val isLoading: Boolean = false,
    val product: List<ProductDto>? = null,
    val error: String = ""
)