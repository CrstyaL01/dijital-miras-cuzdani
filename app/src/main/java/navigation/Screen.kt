package com.digitalmiras.digitalmiras.navigation

sealed class Screen {
    object Splash : Screen()
    object Onboarding : Screen()
    object Login : Screen()
    object Register : Screen()
    object Main : Screen()
}