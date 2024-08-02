package com.ylcd.logincompose.ui.view.navigation

sealed class Screen(val route:String) {
    object MainScreen : Screen("login_screen")


}