package com.ylcd.logincompose.ui.view.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ylcd.logincompose.ui.view.ui.LoginDesing

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {

        composable(route = Screen.MainScreen.route) {
            val loginDesing: LoginDesing = LoginDesing()
            loginDesing.LoginScreen()
        }


    }


}