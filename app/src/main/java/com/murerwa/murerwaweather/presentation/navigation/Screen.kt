package com.murerwa.murerwaweather.presentation.navigation

sealed class Screen(val route: String) {
    object Home : Screen(route = "home_screen")
}