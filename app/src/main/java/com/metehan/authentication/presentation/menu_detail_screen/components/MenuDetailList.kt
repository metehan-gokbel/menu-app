package com.metehan.authentication.presentation.menu_detail_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.metehan.authentication.presentation.menu_detail_screen.MenuDetailViewModel
import com.metehan.authentication.presentation.menu_screen.components.RetrySection

@Composable
fun MenuDetailList(
    viewModel: MenuDetailViewModel = hiltViewModel(),
    merchantId: String
) {

    val productList by remember {
        viewModel.productList
    }

    val groupedProducts = productList.groupBy { it.productCategory.productCategoryName }


    val loadError by remember {
        viewModel.loadError
    }
    val isLoading by remember {
        viewModel.isLoading
    }
    val isSearching by remember {
        viewModel.isSearching
    }

    LaunchedEffect(Unit) {
        viewModel.loadProduct(merchantId)
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ){
            groupedProducts.forEach { (category, productList) ->
                item {
                    CategoryCard(category = category, productList = productList)
                    Spacer(modifier = Modifier.height(2.dp))
                }
            }
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ){
        if(isLoading){
            CircularProgressIndicator(color = MaterialTheme.colorScheme.primary, modifier = Modifier.align(
                Alignment.Center))
        }
        if(loadError.isNotEmpty()){
            RetrySection(error = loadError) {
                viewModel.loadProduct(merchantId = merchantId)
            }
        }
    }
}