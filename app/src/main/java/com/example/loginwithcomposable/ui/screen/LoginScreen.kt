package com.example.loginwithcomposable.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.loginwithcomposable.ui.components.CustomTextField
import com.example.loginwithcomposable.ui.components.HeaderImage
import androidx.compose.runtime.setValue
import androidx.compose.foundation.clickable
import com.example.loginwithcomposable.R
import com.example.loginwithcomposable.utils.PrimaryButton
import com.example.loginwithcomposable.utils.ValidationUtils
import com.example.loginwithcomposable.utils.shake
import androidx.compose.ui.platform.LocalContext
import com.example.loginwithcomposable.helper.SessionManager

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    onSignupClick: () -> Unit,
    onForgotClick: () -> Unit
) {
    val context = LocalContext.current
    val sessionManager = remember { SessionManager(context) }
    var email by remember { mutableStateOf(sessionManager.getEmail()) }
    var password by remember { mutableStateOf(sessionManager.getPassword()) }
    var rememberMe by remember {
        mutableStateOf(sessionManager.isRememberMeChecked())
    }
    var emailError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }
    val isFormValid =
        ValidationUtils.isValidEmail(email) &&
                ValidationUtils.isValidPassword(password)


    Box(modifier = Modifier.fillMaxSize()) {

        HeaderImage(
            imageRes = R.drawable.hearder_image,
            height = 550.dp
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.Center
        ) {

            Spacer(modifier = Modifier.height(320.dp))

            Text(
                text = "Sign in",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(24.dp))

            CustomTextField(
                label = "Email",
                value = email,
                onValueChange = {
                    email = it
                    emailError = email.isNotEmpty() &&
                            !ValidationUtils.isValidEmail(email)
                },
                placeholder = "demo@email.com",
                isError = emailError,
                modifier = Modifier.shake(emailError)
            )

            if (emailError) {
                Text(
                    text = "Invalid email format",
                    color = Color.Red,
                    fontSize = 12.sp
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            CustomTextField(
                label = "Password",
                value = password,
                onValueChange = {
                    password = it
                    passwordError = password.isNotEmpty() &&
                            !ValidationUtils.isValidPassword(password)
                },
                placeholder = "enter your password",
                isPassword = true,
                isError = passwordError,
                modifier = Modifier.shake(passwordError)
            )

            if (passwordError) {
                Text(
                    text = "Password must be at least 6 characters",
                    color = Color.Red,
                    fontSize = 12.sp
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = rememberMe,
                        onCheckedChange = { rememberMe = it }
                    )
                    Text("Remember Me", fontSize = 12.sp)
                }

                Text(
                    text = "Forgot Password?",
                    color = Color(0xFFF47C7C),
                    fontSize = 12.sp,
                    modifier = Modifier.clickable {
                        onForgotClick()
                    }
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            PrimaryButton(
                text = "Login",
                onClick = {
                    val isEmailValid = ValidationUtils.isValidEmail(email)
                    val isPasswordValid = ValidationUtils.isValidPassword(password)

                    emailError = !isEmailValid
                    passwordError = !isPasswordValid

                    if (isEmailValid && isPasswordValid) {

                        sessionManager.saveLogin(true)
                        sessionManager.saveRememberMe(rememberMe)

                        if (rememberMe) {
                            sessionManager.saveUser(email, password)
                        } else {
                            sessionManager.saveUser("", "") // clear saved data
                        }

                        onLoginSuccess()
                    }
                },
                enabled = isFormValid
            )

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text("Don’t have an Account ? ")

                Text(
                    text = "Sign up",
                    color = Color(0xFFF47C7C),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable {
                        onSignupClick()
                    }
                )
            }
        }
    }
}