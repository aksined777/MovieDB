package com.themoviedb.data.database

import com.themoviedb.data.database.dao.MoveDAO
import com.themoviedb.data.database.enity.MoveEntity
import com.themoviedb.domain.entity.Move
import com.themoviedb.domain.repository.LocalRepository
import java.util.*

class LocalRepositoryImp(private val moveDao: MoveDAO) : LocalRepository {
    override fun saveMoves(moves: List<Move>, language: String) {
        moves.forEach { move ->
            val entity = MoveEntity(
                0,
                Date().time,
                move.name,
                move.rating,
                move.script,
                move.poster,
                language
            )
            val id = moveDao.insert(entity)
            move.id = id
        }
    }

    override fun loadMoves(language: String): List<Move> {
        return moveDao.getMoves(language).map { entity ->
            toMove(entity)
        }
    }

    fun toMove(entity: MoveEntity): Move = Move(
        entity.id,
        entity.name,
        entity.rating,
        entity.script,
        entity.poster,
        entity.timestamp,
    )

    override fun loadMove(id: Long): Move = toMove(moveDao.getMove(id))

}