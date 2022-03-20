package com.themoviedb.domain.interactor

import com.themoviedb.domain.repository.LocalRepository

class DetailInteractor(
    private val localRepository: LocalRepository
) {
    fun getMove(id: Long) = localRepository.loadMove(id)
}