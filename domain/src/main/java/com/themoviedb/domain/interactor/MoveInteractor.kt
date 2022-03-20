package com.themoviedb.domain.interactor

import com.themoviedb.domain.entity.Move
import com.themoviedb.domain.repository.LocalRepository
import com.themoviedb.domain.repository.NetworkRepository
import com.themoviedb.domain.repository.PreferencesRepository

class MoveInteractor(
    private val networkRepository: NetworkRepository,
    private val preferencesRepository: PreferencesRepository,
    private val localRepository: LocalRepository,
) {

    suspend fun getMoves(language: String) = networkRepository.getMoves(language)

    fun setLanguage(language: String) {
        preferencesRepository.setLanguage(language)
    }

    fun getLanguage() = preferencesRepository.getLanguage()

    suspend fun saveMoves(list: List<Move>, language: String) {
        localRepository.saveMoves(list, language)
    }

    suspend fun loadMoves(): List<Move> =
        localRepository.loadMoves(preferencesRepository.getLanguage())

}