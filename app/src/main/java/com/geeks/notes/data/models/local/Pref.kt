package com.geeks.notes.data.models.local

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

class Pref(context: Context) {
    private val pref: SharedPreferences =
        context.getSharedPreferences("key_app", Context.MODE_PRIVATE)


    fun saveOnBoard(value: Boolean) {
        pref.edit { putBoolean("key_on_board", value) }
    }

    fun isUserSeen(): Boolean {
        return pref.getBoolean("key_on_board", false)
    }


}