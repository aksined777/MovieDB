package com.themoviedb.data.network

import android.view.textclassifier.TextLanguage
import com.themoviedb.data.network.dto.ResultMoveDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoveApi {
    @GET("/3/discover/movie")
    suspend fun getMoves(@Query("language") language: String): Response<ResultMoveDTO>
}