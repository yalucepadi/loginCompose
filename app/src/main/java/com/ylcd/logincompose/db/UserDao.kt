package com.ylcd.logincompose.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ylcd.logincompose.model.User
import com.ylcd.logincompose.model.operation.UserCredentials
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM users")
    fun getUsers(): Flow<List<User>>

    @Query("SELECT * FROM users where col_mail=:email")
    fun getUsersByEmail(email: String):User

    @Query("SELECT * FROM users where col_id=:id")
    fun getUsersById(id: Int):User

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(user: User)


    @Query("SELECT col_mail, col_password FROM users " +
            "WHERE col_mail = :email AND col_password = :password")
    suspend fun getEmailAndPassword(email: String, password: String): UserCredentials?
}