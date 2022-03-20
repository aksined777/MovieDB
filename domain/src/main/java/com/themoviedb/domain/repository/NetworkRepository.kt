package com.themoviedb.domain.repository

import com.themoviedb.domain.entity.Move

interface NetworkRepository {
    suspend fun getMoves(language:String): List<Move>
}
