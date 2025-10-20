package com.geeks.notes.data.models.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.geeks.notes.data.models.NotesModel

@Dao
interface NoteDao {

    @Query("SELECT *FROM notes_list ORDER BY id DESC")
    fun getNotes(): List<NotesModel>

    @Insert
    fun addNote(notesModel: NotesModel)

    @Delete
    fun deleteNote(notesModel: NotesModel)
    
}