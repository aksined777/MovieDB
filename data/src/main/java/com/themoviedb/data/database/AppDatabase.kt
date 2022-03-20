package com.themoviedb.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.themoviedb.data.database.dao.MoveDAO
import com.themoviedb.data.database.enity.MoveEntity

@Database(
    entities = [MoveEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun moveDAO(): MoveDAO

}