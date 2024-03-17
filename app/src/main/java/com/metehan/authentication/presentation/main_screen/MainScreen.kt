package com.metehan.authentication.presentation.main_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.metehan.authentication.R
import com.metehan.authentication.presentation.main_screen.components.MenuCard

@Composable
fun MainScreen(
    navigateToMenuScreen: () -> Unit,
    navigateToSendBillScreen: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val imageIds = listOf(
            R.drawable.ic_hayat,
            R.drawable.ic_popeyes,
            R.drawable.ic_starbucks,
            R.drawable.ic_trendyol,
            R.drawable.ic_kahvedunyasi,
            R.drawable.ic_burgerking,
        )

        LazyRow{
            items(imageIds){image->
                MenuCard(
                    imageId = image
                )
            }
        }
        Spacer(modifier = Modifier.height(32.dp))
        OutlinedButton(
            onClick = {
                navigateToMenuScreen()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 6.dp, end = 6.dp)
                .height(80.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Text(
                text = "Search Menu",
                color = Color.Black,
                modifier = Modifier.padding(8.dp)
            )
        }
        Spacer(modifier = Modifier.height(32.dp))
        OutlinedButton(
            onClick = {
                navigateToSendBillScreen()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 6.dp, end = 6.dp)
                .height(80.dp),
            shape = RoundedCornerShape(16.dp),

        ) {
            Text(
                text = "Send Us To Bill",
                color = Color.Black,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}