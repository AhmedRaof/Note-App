package com.example.yes.model.userLocal

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.yes.model.userEntity.userDataClass

private const val DATABASE_NAME = "user_database"

@Database(entities = [userDataClass::class], version = 1, exportSchema = false)
abstract class UserDataBase: RoomDatabase() {
    abstract fun userDao() : UserDAO
    
    companion object{
        @Volatile
        private var instance : UserDataBase?= null
        fun getInstance(context : Context) : UserDataBase{
            return instance ?: synchronized(Any()){
                instance ?: buildDatabase(context).also{ instance = it}
            }
        }

        private fun buildDatabase(context: Context): UserDataBase {
            return Room.databaseBuilder(context.applicationContext,UserDataBase::class.java,
                DATABASE_NAME).build()
        }


    }
}