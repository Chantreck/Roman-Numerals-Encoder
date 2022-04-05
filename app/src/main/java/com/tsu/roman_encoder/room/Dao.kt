package com.tsu.roman_encoder.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface Dao {
    @Query("SELECT output FROM data WHERE input = :input")
    suspend fun getOutput(input: String): String

    @Insert
    suspend fun insert(row: Entity)
}