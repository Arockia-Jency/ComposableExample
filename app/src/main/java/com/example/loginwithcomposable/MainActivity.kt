package com.example.loginwithcomposable

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.loginwithcomposable.ui.screen.LoginScreen
import com.example.loginwithcomposable.ui.screen.SplashScreen
import com.example.loginwithcomposable.ui.theme.LoginWithComposableTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.loginwithcomposable.ui.screen.ForgotPasswordScreen
import com.example.loginwithcomposable.ui.screen.HomeScreen
import com.example.loginwithcomposable.ui.screen.SignupScreen
import androidx.compose.ui.platform.LocalContext
import com.example.loginwithcomposable.helper.SessionManager

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LoginWithComposableTheme {
                App()
            }
        }
    }
}

@Composable
fun App() {
    val context = LocalContext.current
    val sessionManager = remember { SessionManager(context) }

    var screen by remember {
        mutableStateOf(
            if (sessionManager.isLoggedIn()) Screen.Home else Screen.Splash
        )
    }

    when (screen) {
        Screen.Splash -> SplashScreen { screen = Screen.Login }

        Screen.Login -> LoginScreen(
            onLoginSuccess = { screen = Screen.Home },
            onSignupClick = { screen = Screen.Signup },
            onForgotClick = { screen = Screen.Forgot }
        )

        Screen.Signup -> SignupScreen(
            onLoginClick = { screen = Screen.Login }
        )

        Screen.Forgot -> ForgotPasswordScreen(
            onLoginClick = { screen = Screen.Login }
        )

        Screen.Home -> HomeScreen(
            onLogout = {
                sessionManager.clearSession()
                screen = Screen.Login
            }
        )
    }
}

sealed class Screen {
    object Splash : Screen()
    object Login : Screen()
    object Signup : Screen()
    object Forgot : Screen()
    object Home : Screen()
}