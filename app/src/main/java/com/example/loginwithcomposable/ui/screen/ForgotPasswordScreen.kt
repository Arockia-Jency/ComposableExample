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
fun ForgotPasswordScreen( onLoginClick: () -> Unit) {
    var passwordError by remember { mutableStateOf(false) }
    var confirmPasswordError by remember { mutableStateOf(false) }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }


    val isButtonEnabled = password.isNotEmpty() &&
            confirmPassword.isNotEmpty()

    val isPasswordValid = ValidationUtils.isValidPassword(password)

    val passwordsDoNotMatch =
        password.isNotEmpty() &&
                confirmPassword.isNotEmpty() &&
                password != confirmPassword

    val isFormValid =
        password.isNotBlank() &&
                confirmPassword.isNotBlank() &&
                isPasswordValid &&
                !passwordsDoNotMatch

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
            Spacer(modifier = Modifier.height(200.dp))

            Text(
                text = "Reset Password",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Text(
                text = "Please enter and confirm your new password.",
                fontSize = 14.sp,
                color = Color.Gray,
                modifier = Modifier.padding(top = 4.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            CustomTextField(
                label = "New Password",
                value = password,
                onValueChange = {
                    password = it

                    passwordError =
                        password.isNotEmpty() &&
                                !ValidationUtils.isValidPassword(password)
                },
                placeholder = "Enter new password",
                isPassword = true,
                isError = passwordError
            )


            Spacer(modifier = Modifier.height(20.dp))

            CustomTextField(
                label = "Confirm Password",
                value = confirmPassword,
                onValueChange = {
                    confirmPassword = it

                    confirmPasswordError =
                        confirmPassword.isNotEmpty() &&
                                password != confirmPassword
                },
                placeholder = "Re-type password",
                isPassword = true,
                isError = confirmPasswordError || passwordsDoNotMatch
            )

            if (passwordError) {
                Text(
                    text = "Password must be at least 6 characters",
                    color = Color.Red,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }

            if (confirmPasswordError) {
                Text(
                    text = "Passwords do not match",
                    color = Color.Red,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }

            Spacer(modifier = Modifier.height(40.dp))

            PrimaryButton(
                text = "Update Password",
                onClick = {
                    val isPasswordValid = ValidationUtils.isValidPassword(password)

                    // Reset first (for shake re-trigger)
                    passwordError = false
                    confirmPasswordError = false

                    passwordError = !isPasswordValid
                    confirmPasswordError = password != confirmPassword

                    if (isPasswordValid && password == confirmPassword) {
                        onLoginClick()
                    }
                },
                enabled = isFormValid
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = "Remembered it? ", color = Color.Gray, fontSize = 14.sp)
                Text(
                    text = "Login",
                    color = Color(0xFFF47C7C),
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    modifier = Modifier.clickable {
                        onLoginClick()
                    }
                )
            }
        }
    }
}