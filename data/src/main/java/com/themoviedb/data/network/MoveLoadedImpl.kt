package com.themoviedb.data.network

import com.themoviedb.data.network.dto.toMoves
import com.themoviedb.domain.entity.Move

class MoveLoadedImpl(val moveApi: MoveApi) : MoveLoaded {

    override suspend fun getMoves(language: String): List<Move> {
        var moves:List<Move>? = null
        val response = moveApi.getMoves(language)
        if (response.isSuccessful) {
            moves = response.body()?.toMoves() ?: listOf()
        } else {
            Exception(response.body()?.toString() ?: "")
        }
        return moves?: ArrayList<Move>()
    }

}