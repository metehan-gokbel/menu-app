package com.metehan.authentication.presentation.menu_detail_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardBackspace
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.metehan.authentication.data.remote.dto.ProductDto
import com.metehan.authentication.presentation.menu_detail_screen.components.MenuDetailAction
import com.metehan.authentication.presentation.menu_detail_screen.components.MenuDetailList
import com.metehan.authentication.presentation.menu_detail_screen.components.MenuDetailSearchBar
import com.metehan.authentication.presentation.menu_screen.components.MenuAction
import com.metehan.authentication.presentation.menu_screen.components.MenuSearchBar
import com.metehan.authentication.presentation.menu_screen.components.MerchantList
import com.metehan.authentication.util.Resource

@Composable
fun MenuDetailScreen(
    viewModel: MenuDetailViewModel = hiltViewModel(),
    merchantId: String
) {

    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
                MenuDetailSearchBar(
                    hint = "Search...",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, bottom = 4.dp, top = 4.dp)
                ){
                    viewModel.searchMenuDetailList(it)
                }

            MenuDetailAction(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp)
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(10.dp))
            MenuDetailList(merchantId = merchantId)
        }
    }


}