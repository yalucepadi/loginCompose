package com.ylcd.logincompose.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remenber")
data class UserRemenber (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "col_id")val id:Int=0,
    @ColumnInfo(name = "col_state")val state:Boolean=false,
    @ColumnInfo(name = "col_UserId")val userId:Int=0,
    )