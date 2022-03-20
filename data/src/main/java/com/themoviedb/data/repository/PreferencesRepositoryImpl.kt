package com.themoviedb.data.repository


import com.themoviedb.data.Prefs
import com.themoviedb.domain.repository.PreferencesRepository

class PreferencesRepositoryImpl(private val prefs: Prefs) : PreferencesRepository {
    override fun getLanguage(): String {
        return prefs.language
    }

    override fun setLanguage(language: String) {
        prefs.language = language
    }

}