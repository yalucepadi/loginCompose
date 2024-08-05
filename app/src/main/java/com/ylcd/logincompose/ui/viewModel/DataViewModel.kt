package com.ylcd.logincompose.ui.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ylcd.logincompose.db.DataBase.Companion.createUser
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
                   UserIntent.GetEmailAndPassword -> TODO()
                   UserIntent.GetUserByEmail -> TODO()
                   UserIntent.GetUserById -> TODO()
               }


           }


        }
    }

    private fun insertUser(user: User) {

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


}