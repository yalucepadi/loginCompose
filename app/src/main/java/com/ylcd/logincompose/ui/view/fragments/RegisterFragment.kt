package com.ylcd.logincompose.ui.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.navigation.compose.rememberNavController
import com.ylcd.logincompose.R
import com.ylcd.logincompose.ui.view.ui.LoginDesing


class RegisterFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return  ComposeView(requireContext()).apply {
            setViewCompositionStrategy(

                ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed
            )
            setContent {
                LoginDesing().LoginScreen(rememberNavController())

            }

        }


}

    private fun inputCheck(
        mail: String,
        tel: String,
        password: String
    ): Boolean {
        return !mail.isNullOrEmpty() &&
                !tel.isNullOrEmpty() &&
                !password.isNullOrEmpty()

    }


}