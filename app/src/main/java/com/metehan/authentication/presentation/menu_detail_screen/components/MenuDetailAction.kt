package com.metehan.authentication.presentation.menu_detail_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.metehan.authentication.R
import com.metehan.authentication.presentation.ui.theme.LightBlue

@Composable
fun MenuDetailAction(
    modifier: Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
    ) {
        MenuDetailIconWithText(painterResource(id = R.drawable.ic_sort), "Sort"){

        }
        MenuDetailIconWithText(painterResource(id = R.drawable.ic_filter), "Filter"){

        }
        MenuDetailIconWithText(painterResource(id = R.drawable.ic_share), "Share"){

        }
    }
}

@Composable
fun MenuDetailIconWithText(
    icon: Painter,
    text: String,
    onClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .height(40.dp)
            .width(LocalConfiguration.current.screenWidthDp.dp / 3.5f)
            .shadow(5.dp, RoundedCornerShape(8.dp))
            .background(Color.White, RoundedCornerShape(8.dp))
            .clickable(onClick = onClick)
            .padding(horizontal = 20.dp, vertical = 12.dp)

    ) {
        Icon(icon, contentDescription = null, tint = LightBlue)
        Spacer(modifier = Modifier.width(10.dp))
        Text(text = text, color = Color.Black, fontSize = 13.sp)
    }
}