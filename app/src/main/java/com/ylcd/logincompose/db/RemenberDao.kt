package com.ylcd.logincompose.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ylcd.logincompose.model.User
import com.ylcd.logincompose.model.UserRemenber
import com.ylcd.logincompose.model.operation.UserCredentials
import kotlinx.coroutines.flow.Flow
@Dao
interface RemenberDao {


    @Query("SELECT * FROM remenber where col_UserId=:id")
    fun getStateByUserId(id: Int): UserRemenber


}