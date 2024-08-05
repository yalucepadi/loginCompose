package com.ylcd.logincompose.ui.viewState

import com.ylcd.logincompose.model.User

sealed class UserState {
    object Loading : UserState()

    data class InsertData(val insertUser: User) : UserState()

    data class Error(val message: String) : UserState()

}