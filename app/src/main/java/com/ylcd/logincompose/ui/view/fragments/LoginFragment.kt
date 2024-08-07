package com.ylcd.logincompose.ui.view.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ylcd.logincompose.db.RoomDb
import com.ylcd.logincompose.model.User
import com.ylcd.logincompose.model.UserRemenber
import com.ylcd.logincompose.repository.UserRepository
import com.ylcd.logincompose.ui.view.navigation.Screen

import com.ylcd.logincompose.ui.view.ui.LoginDesing
import com.ylcd.logincompose.ui.viewModel.DataViewModel
import com.ylcd.logincompose.ui.viewState.UserState
import kotlinx.coroutines.launch

class LoginFragment  : Fragment(){
    private lateinit var mUserViewModel: DataViewModel
    var result=false
    val user: User = User()
    val userRemenber: UserRemenber= UserRemenber()
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
              LoginDesing().LoginScreen(rememberNavController(),this@LoginFragment)

            }

        }
    }
    fun contacSupport(context: Context){

        Toast.makeText(context, "Contacta a soporte", Toast.LENGTH_LONG).show()
    }
    fun remenmber(context: Context){

        Toast.makeText(context, "Recordar", Toast.LENGTH_LONG).show()
    }
    fun registerRemenber(
        state: Boolean,
        userId:Int,
        context: Context
    ): Boolean { // Devuelve un booleano
        val room: RoomDb = RoomDb.getDatabase(context, scope = null)
        mUserViewModel = DataViewModel(repository = UserRepository(room), user,userRemenber,
            context)

        return if (inputCheck(userId)) {
            result = true
            val user = UserRemenber(0,state,userId)
            mUserViewModel.insertUserRember(user)

            Toast.makeText(context, "Successfully added!,${result}", Toast.LENGTH_LONG).show()
            true
        } else {
            result = false
            Toast.makeText(context, "falta data, ${result}", Toast.LENGTH_LONG).show()
            false
        }
    }
    private fun inputCheck(
        userId:Int
    ): Boolean {
        return userId != null

    }

    suspend fun llenarPass(email: String, context: Context): String {
        if (email.isEmpty()) {
            return ""  // o puedes lanzar una excepción si prefieres manejarlo de esa manera
        }
        else{
        val room: RoomDb = RoomDb.getDatabase(context, scope = null)

        mUserViewModel = DataViewModel(repository = UserRepository(room),  user,userRemenber
            ,context)
        var roomUser: User = User()

        mUserViewModel.viewModelScope.launch {
            roomUser = mUserViewModel.getUserByEmail(email)



        }
            .join() // Esperar a que la coroutine termine para asegurar que roomUser se haya inicializado

        return roomUser.password}
    }
    suspend fun llenarId(email: String, context: Context): Int {
        if (email.isEmpty()) {
            return 0  // o puedes lanzar una excepción si prefieres manejarlo de esa manera
        }
        else{
            val room: RoomDb = RoomDb.getDatabase(context, scope = null)

            mUserViewModel = DataViewModel(repository = UserRepository(room),  user,userRemenber
                ,context)
            var roomUser: User = User()

            mUserViewModel.viewModelScope.launch {
                roomUser = mUserViewModel.getUserByEmail(email)



            }
                .join() // Esperar a que la coroutine termine para asegurar que roomUser se haya inicializado

            return roomUser.id}
    }

    fun obtenertoCompare(email: String, context: Context, callback: (String) -> Unit) {
        if (email.isEmpty()) {
            callback("")
            return
        }

        val room: RoomDb = RoomDb.getDatabase(context, scope = null)
        mUserViewModel = DataViewModel(repository = UserRepository(room), user,userRemenber, context)
        mUserViewModel.viewModelScope.launch {
            try {
                val pass = llenarPass(email, context)
                callback(pass.toString())
            } catch (e: Exception) {
                Log.e("LoginFragment", "Error fetching password: ${e.message}")
                callback("")
            }
        }
    }
    fun obtenerId(email: String, context: Context, callback: (String) -> Unit) {
        if (email.isEmpty()) {
            callback("")
            return
        }

        val room: RoomDb = RoomDb.getDatabase(context, scope = null)
        mUserViewModel = DataViewModel(repository = UserRepository(room), user,userRemenber, context)
        mUserViewModel.viewModelScope.launch {
            try {
                val id = llenarId(email,context)
                callback(id.toString())
            } catch (e: Exception) {
                Log.e("LoginFragment", "Error fetching password: ${e.message}")
                callback("")
            }
        }
    }



}