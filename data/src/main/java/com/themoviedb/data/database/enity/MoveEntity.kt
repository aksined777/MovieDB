package com.themoviedb.data.database.enity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class MoveEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val timestamp: Long,
    val name: String,
    val rating: Double = 0.0,
    val script: String = "",
    val poster: String,
    val language:String
)