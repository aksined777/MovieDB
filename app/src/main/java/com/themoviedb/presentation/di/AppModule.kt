package com.themoviedb.presentation.di

import com.themoviedb.presentation.ui.app.AppViewModel
import com.themoviedb.presentation.ui.detail.DetailViewModel
import com.themoviedb.presentation.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {
    viewModel<HomeViewModel>() {
        HomeViewModel(moveInteractor = get(),eventInteractor = get(), context = get())
    }
    viewModel<DetailViewModel>() {
        DetailViewModel(detailInteractor = get())
    }
    viewModel<AppViewModel>() {
        AppViewModel(appInteractor = get(),eventInteractor = get())
    }
}