package com.themoviedb.domain.repository

import com.themoviedb.domain.entity.Move

interface LocalRepository {
    fun saveMoves(moves: List<Move>, language: String)

    fun loadMoves(language: String): List<Move>

    fun loadMove(id: Long): Move
}