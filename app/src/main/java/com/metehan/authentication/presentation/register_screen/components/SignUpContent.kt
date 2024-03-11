package com.metehan.authentication.presentation.register_screen.components

import android.widget.Toast
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
            .padding(padding),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 30.dp, end = 30.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Enter your credential's to register",
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                color = Color.Gray,
                fontFamily = RegularFont,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(8.dp))
            InputTextField(
                labelName = "Name",
                name = name,
                onNameValueChange = {newValue ->
                    name = newValue
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            EmailTextField(
                labelName = "Email",
                email = email,
                onEmailValueChange = {newValue ->
                    email = newValue
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            PasswordTextField(
                labelName = "Password",
                password = password,
                onValueChange = {newValue ->
                    password = newValue
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            PasswordTextField(
                labelName = "Confirm Password",
                password = confirmPassword,
                onValueChange = {newValue ->
                    confirmPassword = newValue
                }
            )
            Button(
                onClick = {
                    if (password == confirmPassword) {
                        signUp(name, email, password)
                    } else {
                        Toast.makeText(context, "Passwords do not match", Toast.LENGTH_LONG).show()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, start = 30.dp, end = 30.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                    contentColor = Color.White,
                ),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(
                    text = "Sign Up",
                    color = Color.White,
                    modifier = Modifier.padding(8.dp)
                )
            }
            Text(
                text = "Already have an account? Sign in",
                modifier = Modifier
                    .padding(15.dp)
                    .clickable {
                        navigateBack()
                    },
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                fontFamily = RegularFont
            )
            Text(
                text = "or Connect with",
                fontWeight = FontWeight.Medium,
                color = Color.Gray
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp), horizontalArrangement = Arrangement.Center
            ) {
                IconButton(onClick = {

                }) {
                    Icon(
                        modifier = Modifier.size(52.dp),
                        painter = painterResource(id = R.drawable.ic_twitter),
                        contentDescription = "Twitter Icon", tint = Color.Unspecified
                    )
                }
                Spacer(modifier = Modifier.width(20.dp))
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        modifier = Modifier.size(50.dp),
                        painter = painterResource(id = R.drawable.ic_google),
                        contentDescription = "Google Icon", tint = Color.Unspecified
                    )
                }
                Spacer(modifier = Modifier.width(20.dp))
                IconButton(onClick = {

                }) {
                    Icon(
                        modifier = Modifier.size(52.dp),
                        painter = painterResource(id = R.drawable.ic_facebook),
                        contentDescription = "Facebook Icon", tint = Color.Unspecified
                    )
                }
            }
        }

    }
}