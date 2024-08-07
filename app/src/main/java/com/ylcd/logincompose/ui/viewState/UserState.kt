package com.ylcd.logincompose.ui.viewState

import com.ylcd.logincompose.model.User
import com.ylcd.logincompose.model.UserRemenber

sealed class UserState {
    object Loading : UserState()

    data class InsertData(val insertUser: User) : UserState()

    data class InsertDataRemenber(val insertUser: UserRemenber) : UserState()
    data class GetPasswordAndEmail(
        val email: String,
        val password: String
    ) : UserState()
    data class GetUserByEmail(
        val email: String
    ) : UserState()
    data class Error(val message: String) : UserState()

}