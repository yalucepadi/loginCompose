package com.ylcd.logincompose.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ylcd.logincompose.model.User
import com.ylcd.logincompose.model.UserRemenber
import kotlinx.coroutines.CoroutineScope


@Database(entities = [User::class, UserRemenber::class],
    version = 2,exportSchema = false)
abstract class RoomDb : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun remenberDao():RemenberDao

    companion object {
        private var INSTANCE: RoomDb? = null

        fun getDatabase(context: Context, scope: CoroutineScope?): RoomDb {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RoomDb::class.java,
                    "room_database"
                )
                    .fallbackToDestructiveMigration()
                    //.addMigrations(MIGRATION_2_3)
                    .allowMainThreadQueries()
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }
}



