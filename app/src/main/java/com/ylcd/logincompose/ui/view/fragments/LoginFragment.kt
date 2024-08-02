package com.ylcd.logincompose.ui.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment

import com.ylcd.logincompose.ui.view.ui.LoginDesing

class LoginFragment  : Fragment(){
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
              LoginDesing().LoginScreen()

            }

        }
    }

}