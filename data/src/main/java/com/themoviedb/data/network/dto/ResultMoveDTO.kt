package com.themoviedb.data.network.dto

import com.google.gson.annotations.SerializedName
import com.themoviedb.domain.entity.Move

class ResultMoveDTO(
    @SerializedName("results")
    val movesDTO: List<MoveDTO>?
)

data class MoveDTO(
    @SerializedName("title")
    val title: String?,
    @SerializedName("vote_average")
    val average: Double?,
    @SerializedName("overview")
    val overview: String?,
    @SerializedName("poster_path")
    val poster: String?
)

fun ResultMoveDTO.toMoves(): List<Move> {
    val moves: ArrayList<Move> =
        (movesDTO?.map {
            it.toMove()
        } ?: listOf<Move>()) as ArrayList<Move>
    return moves
}

fun MoveDTO.toMove(): Move =
    Move(0, this.title ?: "", this.average ?: 0.0, this.overview ?: "", this.poster ?: "")
