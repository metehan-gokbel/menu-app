package com.metehan.authentication.presentation.register_screen.components

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.metehan.authentication.R
import com.metehan.authentication.presentation.ui.theme.LightBlue
import com.metehan.authentication.util.components.InputTextField
import com.metehan.authentication.presentation.ui.theme.RegularFont
import com.metehan.authentication.util.components.EmailTextField
import com.metehan.authentication.util.components.PasswordTextField

@Composable
fun SignUpContent(
    padding: PaddingValues,
    signUp: (name: String, email: String, password: String) -> Unit,
    navigateBack: () -> Unit
) {
    var name by rememberSaveable {
        mutableStateOf("")
    }
    var email by rememberSaveable {
        mutableStateOf("")
    }
    var password by rememberSaveable {
        mutableStateOf("")
    }
    var confirmPassword by rememberSaveable {
        mutableStateOf("")
    }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 30.dp, end = 30.dp, bottom = 20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "Sign Up",
            fontWeight = FontWeight.Medium,
            fontSize = 22.sp,
            color = Color.Black,
            fontFamily = RegularFont,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Text(
            text = "Enter your credential's to register",
            fontWeight = FontWeight.Medium,
            color = Color.Gray,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(8.dp))
        InputTextField(
            labelName = "Name",
            name = name,
            onNameValueChange = { newValue ->
                name = newValue
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        EmailTextField(
            labelName = "Email",
            email = email,
            onEmailValueChange = { newValue ->
                email = newValue
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        PasswordTextField(
            labelName = "Password",
            password = password,
            onValueChange = { newValue ->
                password = newValue
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        PasswordTextField(
            labelName = "Confirm Password",
            password = confirmPassword,
            onValueChange = { newValue ->
                confirmPassword = newValue
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                if (password == confirmPassword) {
                    signUp(name, email, password)
                } else {
                    Toast.makeText(context, "Passwords do not match", Toast.LENGTH_LONG).show()
                }
            },
            modifier = Modifier
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = LightBlue,
                contentColor = Color.White,
            ),
            shape = CircleShape
        ) {
            Text(
                text = "Sign Up",
                color = Color.White,
                modifier = Modifier.padding(8.dp)
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "Already have an account?",
            modifier = Modifier
                .padding(15.dp),
            fontWeight = FontWeight.Bold,
            color = LightBlue,
            fontFamily = RegularFont
        )
        OutlinedButton(
            onClick = {
                navigateBack()
            },
            modifier = Modifier.fillMaxWidth(),
            shape = CircleShape,
            border = BorderStroke(1.dp, LightBlue)
        ) {
            Text(
                text = "Sign In",
                color = LightBlue,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}