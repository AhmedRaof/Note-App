package com.example.yes.model.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.yes.model.entity.NoteDataClass

private const val DATABASE_NAME = "note_database"
@Database(entities = [NoteDataClass :: class], version = 1, exportSchema = false)
abstract class NoteDataBase : RoomDatabase() {

    abstract fun NoteDAO():NoteDao
    companion object{
        @Volatile
        private  var instance : NoteDataBase? = null
        fun getInstance(context : Context) : NoteDataBase{
            return instance?: synchronized(Any()){
                instance ?: buildDatabase(context).also{ instance = it}
            }
        }
        private fun buildDatabase(context : Context) : NoteDataBase{
            return Room.databaseBuilder(context.applicationContext,NoteDataBase::class.java,
                DATABASE_NAME).build()
        }
    }
}