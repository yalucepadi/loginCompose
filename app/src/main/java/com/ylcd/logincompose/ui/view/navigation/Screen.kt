package com.ylcd.logincompose.ui.view.navigation

sealed class Screen(val route:String) {
    object MainScreen : Screen("login_screen")
    object RegisterScreen : Screen("register_screen")
    object ProfileScreen : Screen("profile_screen")

    fun withArg(args:String):String {
        return "$route/$args"
    }


}