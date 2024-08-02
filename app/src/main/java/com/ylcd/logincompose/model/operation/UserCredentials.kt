package com.ylcd.logincompose.model.operation

import androidx.room.ColumnInfo

data class UserCredentials(
    @ColumnInfo(name = "col_mail") val mail: String,
    @ColumnInfo(name = "col_password") val password: String
)

