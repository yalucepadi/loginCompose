package com.ylcd.logincompose.ui.view.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.room.Dao
import com.ylcd.logincompose.ui.view.fragments.LoginFragment
import com.ylcd.logincompose.ui.view.fragments.RegisterFragment
import com.ylcd.logincompose.ui.view.ui.LoginDesing
import com.ylcd.logincompose.ui.view.ui.ProfileDesing
import com.ylcd.logincompose.ui.view.ui.RegisterDesing

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {

        composable(route = Screen.MainScreen.route) {
            val loginDesing: LoginDesing = LoginDesing()
            loginDesing.LoginScreen(navController,LoginFragment())
        }

        composable(route = Screen.RegisterScreen.route) {
            val registerDesing: RegisterDesing = RegisterDesing()
            registerDesing.RegisterScreen(navController, RegisterFragment())
        }
        composable(
            route = Screen.ProfileScreen.route + "/{email}",
            arguments = listOf(
                navArgument("email") {
                    type = NavType.StringType
                }
            )
        ) { entry ->


            val email = entry.arguments?.getString("email") ?: ""
            val profileDesing: ProfileDesing = ProfileDesing()
            profileDesing.ProfileScreen( navController, email )
        }


    }


}