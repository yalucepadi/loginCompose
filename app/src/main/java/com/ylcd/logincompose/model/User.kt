package com.ylcd.logincompose.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "col_id")val id:Int=0,
    @ColumnInfo(name = "col_mail")val mail:String="",
    @ColumnInfo(name = "col_tel")val tel:String="",
    @ColumnInfo(name = "col_password")val password:String="",
)