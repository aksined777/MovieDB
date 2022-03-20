package com.themoviedb.presentation.di

import com.themoviedb.data.Prefs
import com.themoviedb.data.database.DatabaseManager
import com.themoviedb.data.database.LocalRepositoryImp
import com.themoviedb.data.network.MoveLoaded
import com.themoviedb.data.network.MoveLoadedImpl
import com.themoviedb.data.network.RetrofitProvider
import com.themoviedb.data.repository.EventRepositoryImpl
import com.themoviedb.data.repository.NetworkRepositoryImpl
import com.themoviedb.data.repository.PreferencesRepositoryImpl
import com.themoviedb.domain.repository.EventRepository
import com.themoviedb.domain.repository.LocalRepository
import com.themoviedb.domain.repository.NetworkRepository
import com.themoviedb.domain.repository.PreferencesRepository
import org.koin.dsl.module

val dataModule = module {
    single<RetrofitProvider> { RetrofitProvider() }
    single<Prefs> { Prefs(context = get()) }
    single<DatabaseManager> { DatabaseManager(context = get()) }
    single<PreferencesRepository> { PreferencesRepositoryImpl(get()) }
    single<NetworkRepository> { NetworkRepositoryImpl(get()) }
    single<EventRepository> { EventRepositoryImpl() }
    single<LocalRepository> { LocalRepositoryImp((get() as DatabaseManager).getMoveDAO()) }
    factory<MoveLoaded> {
        MoveLoadedImpl(
            (get() as RetrofitProvider).getApi()
        )
    }
}