package com.themoviedb.data.network

import com.themoviedb.domain.entity.Move

interface MoveLoaded {
    suspend fun getMoves(language:String): List<Move>
}