package com.geeks.notes.data.models.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.geeks.notes.data.models.NotesModel

@Database(entities = [NotesModel::class], version = 1)
abstract class AppDataBase: RoomDatabase() {
    abstract fun dao(): NoteDao

}