package com.digitalmiras.digitalmiras

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import com.digitalmiras.digitalmiras.screens.*
import com.digitalmiras.digitalmiras.ui.theme.DigitalMirasTheme
import navigation.Screen
import screens.LoginScreen
import screens.RegisterScreen
import screens.SplashScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DigitalMirasTheme {
                DigitalMirasMainApp()
            }
        }
    }
}

@Composable
fun DigitalMirasMainApp() {
    var currentScreen by remember { mutableStateOf<Screen>(Screen.Splash) }
    var isLoggedIn by remember { mutableStateOf(false) }
    var showOnboarding by remember { mutableStateOf(true) }

    when (currentScreen) {
        Screen.Splash -> SplashScreen {
            currentScreen = if (showOnboarding) Screen.Onboarding else Screen.Login
        }
        Screen.Onboarding -> OnboardingScreen {
            showOnboarding = false
            currentScreen = Screen.Login
        }
        Screen.Login -> LoginScreen(
            onLoginSuccess = {
                isLoggedIn = true
                currentScreen = Screen.Main
            },
            onNavigateToRegister = { currentScreen = Screen.Register }
        )
        Screen.Register -> RegisterScreen(
            onRegisterSuccess = {
                isLoggedIn = true
                currentScreen = Screen.Main
            },
            onNavigateToLogin = { currentScreen = Screen.Login }
        )
        Screen.Main -> MainApp(
            onLogout = {
                isLoggedIn = false
                currentScreen = Screen.Login
            }
        )
    }
}