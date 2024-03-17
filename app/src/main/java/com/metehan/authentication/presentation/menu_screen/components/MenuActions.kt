package com.metehan.authentication.presentation.menu_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.metehan.authentication.R
import com.metehan.authentication.presentation.ui.theme.LightBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuAction(
    modifier: Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
    ) {
        MenuIconWithText(painterResource(id = R.drawable.ic_sort), "Sort"){

        }
        MenuIconWithText(painterResource(id = R.drawable.ic_filter), "Filter"){

        }
        MenuIconWithText(painterResource(id = R.drawable.ic_share), "Share"){

        }
    }
}

@Composable
fun MenuIconWithText(
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

@Preview(showBackground = true)
@Composable
fun PreviewSearchMenuTopBar() {
//    MenuAction()
}