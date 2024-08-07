package com.ylcd.logincompose.ui.view.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.navigation.compose.rememberNavController
import com.ylcd.logincompose.db.RoomDb
import com.ylcd.logincompose.model.User
import com.ylcd.logincompose.repository.UserRepository
import com.ylcd.logincompose.ui.viewModel.DataViewModel
import android.widget.Toast
import com.ylcd.logincompose.ui.view.ui.RegisterDesing


class RegisterFragment : Fragment() {
    private lateinit var mUserViewModel: DataViewModel
    private val user: User = User()
    var result=false
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
             RegisterDesing().RegisterScreen(navController = rememberNavController(),
                 registerFragment =this@RegisterFragment )

            }

        }


}
    fun register(
        mail: String,
        tel:String,
        password: String,
        context: Context
    ): Boolean { // Devuelve un booleano
        val room: RoomDb = RoomDb.getDatabase(context, scope = null)
        mUserViewModel = DataViewModel(repository = UserRepository(room), user, context)

        return if (inputCheck(mail, tel, password)) {
            result = true
            val user = User(0,mail,tel,password)
            mUserViewModel.insertUser(user)

            Toast.makeText(context, "Successfully added!,${result}", Toast.LENGTH_LONG).show()
            true
        } else {
            result = false
            Toast.makeText(context, "falta data, ${result}", Toast.LENGTH_LONG).show()
            false
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