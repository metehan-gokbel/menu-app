package com.metehan.authentication.presentation.verify_email_screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun VerifyEmailContent(
    padding: PaddingValues,
    reloadUser: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(padding).padding(start = 32.dp, end = 32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.clickable {
                reloadUser()
            },
            text = "Already verified?",
            fontSize = 16.sp,
            textDecoration = TextDecoration.Underline
        )
        Spacer(
            modifier = Modifier.height(8.dp)
        )
        Text(
            text = "If not, please also check the spam folder.",
            fontSize = 15.sp
        )
    }
}