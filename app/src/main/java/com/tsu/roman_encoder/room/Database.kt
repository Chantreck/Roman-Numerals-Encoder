package com.tsu.roman_encoder.room

import android.content.Context
import androidx.room.Room

object Database {
    private var instance: AppDatabase? = null

    fun getInstance(context: Context): AppDatabase = synchronized(this) {
        instance ?: build(context.applicationContext).also { instance = it }
    }

    private fun build(context: Context): AppDatabase = Room
        .databaseBuilder(context, AppDatabase::class.java, "currency_db")
        .fallbackToDestructiveMigration()
        .build()
}