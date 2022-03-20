package com.themoviedb.domain.interactor

import com.themoviedb.domain.repository.PreferencesRepository

class AppInteractor(private val preferencesRepository: PreferencesRepository) {
    fun getLanguage() = preferencesRepository.getLanguage()
}