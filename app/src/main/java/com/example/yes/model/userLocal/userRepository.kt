package com.example.yes.model.userLocal

import com.example.yes.model.userEntity.userDataClass

interface userRepository {

    suspend fun insertUser(user : userDataClass)

    suspend fun getUsers():List<userDataClass>
}