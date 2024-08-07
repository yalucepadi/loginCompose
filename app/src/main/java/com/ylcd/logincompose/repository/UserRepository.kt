package com.ylcd.logincompose.repository

import com.ylcd.logincompose.db.RemenberDao
import com.ylcd.logincompose.db.RoomDb
import com.ylcd.logincompose.db.UserDao
import com.ylcd.logincompose.model.User
import com.ylcd.logincompose.model.UserRemenber
import com.ylcd.logincompose.model.operation.UserCredentials
import kotlinx.coroutines.flow.Flow

class UserRepository(private val roomDb: RoomDb) : UserDao,RemenberDao{
    override fun getUsers(): Flow<List<User>> {
        return roomDb.userDao().getUsers()
    }

    override fun getUsersByEmail(email: String): User {
       return  roomDb.userDao().getUsersByEmail(email)
    }

    override fun getUsersById(id: Int): User {
        return  roomDb.userDao().getUsersById(id)
    }

    override suspend fun insertUser(user: User) {
        return roomDb.userDao().insertUser(user)
    }

    override suspend fun getEmailAndPassword(email: String, password: String): UserCredentials? {
        return roomDb.userDao().getEmailAndPassword(email, password)
    }

    override fun getStateByUserId(id: Int): UserRemenber {
        return roomDb.remenberDao().getStateByUserId(id)
    }

    override suspend fun insertByEmail(userRemenber: UserRemenber) {
        return roomDb.remenberDao().insertByEmail(userRemenber)
    }


}