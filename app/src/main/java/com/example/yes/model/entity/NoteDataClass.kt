package com.example.yes.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_table")
data class NoteDataClass (
    @PrimaryKey(autoGenerate = true)
    var id:Int,
    @ColumnInfo(name = "note_title")
    var title:String,
    var note_content:String,
    var dataAndtime:String,

    )