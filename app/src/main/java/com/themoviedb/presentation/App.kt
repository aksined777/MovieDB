package com.themoviedb.presentation

import android.app.Application
import android.content.Context
import com.themoviedb.presentation.di.appModule
import com.themoviedb.presentation.di.dataModule
import com.themoviedb.presentation.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listOf(appModule, dataModule, domainModule))
        }
    }


}