package com.metehan.authentication.presentation.register_screen.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import com.metehan.authentication.util.components.BackIcon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpTopBar(
    title: String,
    navigateBack: () -> Unit
) {
    TopAppBar (
        title = {
            Text(
                text = title
            )
        },
        navigationIcon = {
            BackIcon(
                navigateBack = navigateBack
            )
        }
    )
}