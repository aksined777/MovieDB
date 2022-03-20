package com.themoviedb.domain.repository

import kotlinx.coroutines.flow.MutableSharedFlow

interface EventRepository {
    suspend fun getChangeSwitchLanguage(): MutableSharedFlow<String>
    suspend fun setChangeSwitchLanguage(lang: String)
}