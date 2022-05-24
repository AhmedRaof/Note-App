package com.example.yes.model.local

import com.example.yes.model.entity.NoteDataClass

interface LocalRepository {

    suspend fun getNotes(): List<NoteDataClass>
    suspend fun deleteNote(note : NoteDataClass)
    suspend fun insertOrupdate(note : NoteDataClass)
    suspend fun getSpecificNote(id : Int)
}