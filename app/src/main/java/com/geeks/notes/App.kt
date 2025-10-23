package com.geeks.notes

import android.app.Application
import androidx.room.Room
import com.geeks.notes.data.models.local.AppDataBase

class App: Application() {
    companion object{
        lateinit var db: AppDataBase
    }

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(applicationContext,
            AppDataBase::class.java, "notes_data")
            .allowMainThreadQueries()
            .build()
    }

}