package com.example.yes.model.userLocal

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.yes.model.userEntity.userDataClass

@Dao
interface UserDAO {
    @Insert()
    suspend fun insertUser(user : userDataClass)

    @Query(value = "select * from user_table")
    suspend fun getUsers():List<userDataClass>
}