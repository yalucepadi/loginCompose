package com.ylcd.logincompose.db

import android.content.Context
import androidx.room.Room

import com.ylcd.logincompose.enum.DatabaseType
import com.ylcd.logincompose.model.User
import com.ylcd.logincompose.repository.UserRepository

class DataBase {
    companion object {
        suspend fun createUser(context: Context, databaseType: DatabaseType,
                               user:User ):UserDao{
            return when(databaseType){

                DatabaseType.ROOM ->{
                        val roomDb:RoomDb = RoomDb.getDatabase(context,null)
                    val repository: UserRepository = UserRepository(roomDb)
                    repository.insertUser(user)
                    createRoomUserDao( context = context)

                }

            }

        }

        suspend fun createRoomUserDao(context: Context): UserDao {
            val roomDb = Room.databaseBuilder(
                context,
                RoomDb::class.java,
                "list_database"
            ).build()
            return roomDb.userDao()
        }


    }

}