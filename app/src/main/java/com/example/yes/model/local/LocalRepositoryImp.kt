package com.example.yes.model.local

import com.example.yes.model.entity.NoteDataClass
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocalRepositoryImp(private val db : NoteDataBase) : LocalRepository {
    override suspend fun getNotes() = withContext(Dispatchers.IO){
        db.NoteDAO().getNotes()
    }

    override suspend fun deleteNote(note: NoteDataClass) {
       withContext(Dispatchers.IO){
           db.NoteDAO().deleteNote(note)
       }
    }

    override suspend fun insertOrupdate(note: NoteDataClass) {
        withContext(Dispatchers.IO){
            db.NoteDAO().insertOrupdate(note)
        }
    }

    override suspend fun getSpecificNote(id : Int){
        withContext(Dispatchers.IO){
            db.NoteDAO().getSpecificNote(id)
        }
    }

}