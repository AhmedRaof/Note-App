package com.example.yes.adapters

import com.example.yes.model.entity.NoteDataClass

interface OnListItemClick {
    fun onNoteClick(note : NoteDataClass)
}