package com.themoviedb.data.repository

import com.themoviedb.domain.repository.EventRepository
import kotlinx.coroutines.flow.MutableSharedFlow

class EventRepositoryImpl : EventRepository {

    private val changeLanguageSharedFlow = MutableSharedFlow<String>()

    override suspend fun getChangeSwitchLanguage() = changeLanguageSharedFlow

    override suspend fun setChangeSwitchLanguage(lang: String) {
        changeLanguageSharedFlow.emit(lang)
    }


}