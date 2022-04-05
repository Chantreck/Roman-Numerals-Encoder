package com.tsu.roman_encoder.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "data")
data class Entity(
    @PrimaryKey(autoGenerate = false)
    val input: String,
    val output: String
)
