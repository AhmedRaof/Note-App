package com.example.yes.model.local

import androidx.room.*
import com.example.yes.model.entity.NoteDataClass

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrupdate(note : NoteDataClass)

    @Delete
    suspend fun deleteNote(note : NoteDataClass)

    @Query("select * from note_table")
    suspend fun getNotes(): List<NoteDataClass>

    @Query("SELECT * FROM note_table WHERE id = :id")
    suspend fun getSpecificNote(id: Int): NoteDataClass


}