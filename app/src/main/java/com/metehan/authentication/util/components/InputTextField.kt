package com.metehan.authentication.util.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.KeyboardType
import kotlinx.coroutines.job

@Composable
fun InputTextField(
    labelName: String,
    name: String,
    onNameValueChange: (newValue: String) -> Unit
) {
    val focusRequester = FocusRequester()

    OutlinedTextField(
        value = name,
        onValueChange = { newValue ->
            onNameValueChange(newValue)
        },
        label = {
            Text(
                text = labelName
            )
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text
        ),
        modifier = Modifier.focusRequester(focusRequester)
    )

    LaunchedEffect(Unit) {
        coroutineContext.job.invokeOnCompletion {
            focusRequester.requestFocus()
        }
    }
}