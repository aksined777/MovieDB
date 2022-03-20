package com.themoviedb.data.repository

import com.themoviedb.data.network.MoveLoaded
import com.themoviedb.domain.repository.NetworkRepository

class NetworkRepositoryImpl(var loader: MoveLoaded) : NetworkRepository {

    override suspend fun getMoves(language: String) = loader.getMoves(language)

}