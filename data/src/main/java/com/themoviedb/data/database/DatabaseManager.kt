package com.themoviedb.data.database

import android.content.Context
import androidx.room.Room
import com.themoviedb.data.database.dao.MoveDAO

class DatabaseManager(private val context: Context) {

    private val appDatabase: AppDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        APP_DATABASE_NAME
    ).build()

    companion object {
        const val APP_DATABASE_NAME = "app_database.db"
    }


    fun getMoveDAO(): MoveDAO {
        return appDatabase.moveDAO()
    }

}