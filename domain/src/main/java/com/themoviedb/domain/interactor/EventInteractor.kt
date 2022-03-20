package com.themoviedb.domain.interactor

import com.themoviedb.domain.repository.EventRepository
import kotlinx.coroutines.flow.MutableSharedFlow


class EventInteractor(
    private val eventRepository: EventRepository
) {

    suspend fun getSwitchLanguage(): MutableSharedFlow<String> =
        eventRepository.getChangeSwitchLanguage()

    suspend fun setSwitchLanguage(lang: String) = eventRepository.setChangeSwitchLanguage(lang)

}