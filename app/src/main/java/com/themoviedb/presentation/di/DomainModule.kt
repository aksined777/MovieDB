package com.themoviedb.presentation.di

import com.themoviedb.domain.interactor.AppInteractor
import com.themoviedb.domain.interactor.DetailInteractor
import com.themoviedb.domain.interactor.EventInteractor
import com.themoviedb.domain.interactor.MoveInteractor
import org.koin.dsl.module

val domainModule = module {
    factory {
        MoveInteractor(
            networkRepository = get(),
            preferencesRepository = get(),
            localRepository = get()
        )
    }
    factory {
        DetailInteractor(
            localRepository = get()
        )
    }
    factory {
        AppInteractor(
            preferencesRepository = get()
        )
    }
    factory {
        EventInteractor(
            eventRepository = get()
        )
    }
}