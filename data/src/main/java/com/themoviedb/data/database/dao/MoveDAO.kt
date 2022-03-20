package com.themoviedb.data.database.dao

import androidx.room.*
import com.themoviedb.data.database.enity.MoveEntity


@Dao
interface MoveDAO {

    @Query("SELECT * FROM MoveEntity WHERE language=:language ORDER BY timestamp DESC ")
    fun getMoves(language: String): List<MoveEntity>

    @Query("SELECT * FROM MoveEntity WHERE (name LIKE :word) ORDER BY timestamp DESC")
    fun getMoveByName(word: String): List<MoveEntity>

    @Query("SELECT * FROM MoveEntity WHERE id=:id")
    fun getMove(id: Long): MoveEntity

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(entity: MoveEntity): Long

    @Update
    fun update(entity: MoveEntity)

    @Delete
    fun delete(entity: MoveEntity)

}