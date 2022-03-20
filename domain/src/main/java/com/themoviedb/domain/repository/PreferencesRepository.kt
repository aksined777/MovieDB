package com.themoviedb.domain.repository

interface PreferencesRepository {
    fun getLanguage(): String
    fun setLanguage(language: String)
}