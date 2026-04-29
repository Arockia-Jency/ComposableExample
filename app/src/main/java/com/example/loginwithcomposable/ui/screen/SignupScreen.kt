package com.example.loginwithcomposable.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.loginwithcomposable.components.CustomTextField
import com.example.loginwithcomposable.components.HeaderImage
import androidx.compose.runtime.setValue
import com.example.loginwithcomposable.R
import com.example.loginwithcomposable.utils.PrimaryButton
import com.example.loginwithcomposable.utils.ValidationUtils
@Composable
fun SignupScreen( onLoginClick: () -> Unit) {
    var emailError by remember { mutableStateOf(false) }
    var phoneError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }
    var confirmPasswordError by remember { mutableStateOf(false) }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    val isFormValid =
        ValidationUtils.isValidEmail(email) &&
                ValidationUtils.isValidPhone(phone) &&
                ValidationUtils.isValidPassword(password) &&
                password == confirmPassword


    val isButtonEnabled = email.isNotEmpty() && phone.isNotEmpty() && password.isNotEmpty() &&
            confirmPassword.isNotEmpty()


    Box(modifier = Modifier.fillMaxSize()) {

        HeaderImage(
            imageRes = R.drawable.signup_header,
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
                text = "Sign up",
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
                isError = emailError
            )

            if (emailError) {
                Text("Invalid email format", color = Color.Red, fontSize = 12.sp)
            }

            Spacer(modifier = Modifier.height(16.dp))

            CustomTextField(
                label = "Phone no",
                value = phone,
                onValueChange = {
                    phone = it
                    phoneError = phone.isNotEmpty() &&
                            !ValidationUtils.isValidPhone(phone)
                },
                placeholder = "+00 000-0000-000",
                isError = phoneError
            )

            if (phoneError) {
                Text("Enter valid 10-digit phone number", color = Color.Red, fontSize = 12.sp)
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
                placeholder = "enter password",
                isPassword = true,
                isError = passwordError
            )

            if (passwordError) {
                Text("Password must be at least 6 characters", color = Color.Red, fontSize = 12.sp)
            }

            Spacer(modifier = Modifier.height(16.dp))

            CustomTextField(
                label = "Confirm Password",
                value = confirmPassword,
                onValueChange = {
                    confirmPassword = it
                    confirmPasswordError =
                        confirmPassword.isNotEmpty() &&
                                password != confirmPassword
                },
                placeholder = "confirm password",
                isPassword = true,
                isError = confirmPasswordError
            )

            if (confirmPasswordError) {
                Text("Passwords do not match", color = Color.Red, fontSize = 12.sp)
            }

            Spacer(modifier = Modifier.height(24.dp))

            PrimaryButton(
                text = "Create Account",
                onClick = {},
                enabled = isFormValid
            )

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text("Already have an Account! ")

                Text(
                    text = "Login",
                    color = Color(0xFFF47C7C),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable {
                        onLoginClick()
                    }
                )
            }
        }
    }
}