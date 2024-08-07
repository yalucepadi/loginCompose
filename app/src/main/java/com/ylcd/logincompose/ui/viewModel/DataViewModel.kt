package com.ylcd.logincompose.ui.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ylcd.logincompose.db.DataBase.Companion.createUser
import com.ylcd.logincompose.db.DataBase.Companion.selectEmailandPassword
import com.ylcd.logincompose.db.DataBase.Companion.selectUserByEmail
import com.ylcd.logincompose.enum.DatabaseType
import com.ylcd.logincompose.model.User
import com.ylcd.logincompose.repository.UserRepository
import com.ylcd.logincompose.ui.intent.UserIntent
import com.ylcd.logincompose.ui.viewState.UserState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

class DataViewModel(
    private val repository: UserRepository,
    private val user: User,
    context: Context

) : ViewModel() {
    private val context by lazy { context }
    val userIntent = Channel <UserIntent>(Channel.UNLIMITED)
    val dataStateUser = MutableStateFlow<UserState>(UserState.Loading)

init {
      handleIntentUser()
}
    private fun handleIntentUser(){

        viewModelScope.launch {
           userIntent.consumeAsFlow().collect{
               when(it){
                 is UserIntent.InsertUser -> insertUser(user)
                   UserIntent.FetchUser -> TODO()
                   UserIntent.GetEmailAndPassword ->getEmailAndPassword(user.mail,user.password)
                   UserIntent.GetUserByEmail -> getUserByEmail(user.mail)
                   UserIntent.GetUserById -> TODO()
               }


           }


        }
    }

     fun insertUser(user: User) {

        viewModelScope.launch {
             dataStateUser.value = UserState.Loading

            dataStateUser.value = try {

                val user = User(0, user.mail, user.tel, user.password)
                createUser(context,DatabaseType.ROOM, user)
                UserState.InsertData(user)

            }
            catch (e: Exception){
                UserState.Error(e.localizedMessage!!)
            }

        }

    }

    suspend fun getEmailAndPassword(email: String, password: String) {
        dataStateUser.value = UserState.Loading
        try {
            // Llamar a la función para obtener las credenciales del usuario desde la base de datos
            val userCredentials = selectEmailandPassword(context, DatabaseType.ROOM, email, password)

            // Actualizar el estado con las credenciales obtenidas
            dataStateUser.value = UserState.GetPasswordAndEmail(userCredentials.mail,
                userCredentials.password)
        } catch (e: Exception) {
            // En caso de error, actualizar el estado con un estado de error
            dataStateUser.value = UserState.Error(e.localizedMessage!!)
        }
    }
    suspend fun getUserByEmail(email: String):User {
        dataStateUser.value = UserState.Loading
        try {
            // Llamar a la función para obtener las credenciales del usuario desde la base de datos
            selectUserByEmail(context,DatabaseType.ROOM,email)

            // Actualizar el estado con las credenciales obtenidas
            dataStateUser.value = UserState.GetUserByEmail(email)
        } catch (e: Exception) {
            // En caso de error, actualizar el estado con un estado de error
            dataStateUser.value = UserState.Error(e.localizedMessage!!)
        }
        return  selectUserByEmail(context,DatabaseType.ROOM,email)
    }


}