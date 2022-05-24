package com.example.yes.model.userLocal

import com.example.yes.model.local.NoteDataBase
import com.example.yes.model.userEntity.userDataClass
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class userRepositoryImp(private val DB : UserDataBase):userRepository {
    override suspend fun insertUser(user: userDataClass) {
        withContext(Dispatchers.IO){
            DB.userDao().insertUser(user)
        }
    }

    override suspend fun getUsers()= withContext(Dispatchers.IO){
         DB.userDao().getUsers()
    }
}