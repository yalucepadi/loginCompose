package com.ylcd.logincompose.ui.intent

sealed class UserIntent {
    object FetchUser : UserIntent()
    object InsertUser : UserIntent()
    object GetEmailAndPassword : UserIntent()
    object GetUserByEmail: UserIntent()

    object GetUserById: UserIntent()

}