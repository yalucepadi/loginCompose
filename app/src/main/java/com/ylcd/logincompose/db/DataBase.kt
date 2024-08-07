package com.ylcd.logincompose.db

import android.content.Context
import android.util.Log
import androidx.room.Room

import com.ylcd.logincompose.enum.DatabaseType
import com.ylcd.logincompose.model.User
import com.ylcd.logincompose.model.operation.UserCredentials
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
        suspend fun selectEmailandPassword(context: Context, databaseType: DatabaseType,
                                           email:String, password:String): UserCredentials {
            return when (databaseType) {
                DatabaseType.ROOM -> {
                    val roomDb: RoomDb = RoomDb.getDatabase(context, null)
                    val repository: UserRepository = UserRepository(roomDb)

                    val userCredentials = repository.getEmailAndPassword(email, password)

                    Log.i( "userCredentials", "selectEmailandPassword:" +
                            "  ${userCredentials?.mail}\n" +
                            "                        ${userCredentials?.password}")

                    userCredentials ?: throw IllegalArgumentException("Usuario no encontrado")
                }
            }
        }
        suspend fun selectUserByEmail(context: Context, databaseType: DatabaseType, email: String): User {
            if (email.isEmpty()) {
                throw IllegalArgumentException("El email no puede estar vacío")
            }

            return when (databaseType) {
                DatabaseType.ROOM -> {
                    val roomDb: RoomDb = RoomDb.getDatabase(context, null)
                    val repository: UserRepository = UserRepository(roomDb)
                    val userRoom = repository.getUsersByEmail(email)
                    userRoom ?: throw IllegalArgumentException("Usuario no encontrado")
                }
            }
        }


    }

}