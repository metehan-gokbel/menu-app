package com.metehan.authentication.util.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Person2
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import kotlinx.coroutines.job

@Composable
fun InputTextField(
    labelName: String,
    name: String,
    onNameValueChange: (newValue: String) -> Unit
) {
    OutlinedTextField(
        value = name,
        onValueChange = { newValue ->
            onNameValueChange(newValue)
        },
        label = {
            Text(
                text = labelName,
                color = Color.LightGray
            )
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text
        ),
        modifier = Modifier
            .fillMaxWidth(),
        trailingIcon = {
            Icon(
                imageVector = Icons.Filled.Person,
                contentDescription = null,
                tint = Color.LightGray
            )
        }
    )
}