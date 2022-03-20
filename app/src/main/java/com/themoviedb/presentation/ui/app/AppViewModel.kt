package com.themoviedb.presentation.ui.app

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.themoviedb.domain.interactor.AppInteractor
import com.themoviedb.domain.interactor.EventInteractor
import com.themoviedb.presentation.ui.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AppViewModel(
    private val appInteractor: AppInteractor,
    private val eventInteractor: EventInteractor
) : BaseViewModel() {

    private val changeEnglishLanguage: MutableLiveData<String> = MutableLiveData()

    fun changeEnglishLanguage(): LiveData<String> = changeEnglishLanguage

    fun getLanguage() = appInteractor.getLanguage()

    fun handleSwitchLanguage() {
        coroutineScope.launch {
            eventInteractor.getSwitchLanguage().collect() {
                withContext(Dispatchers.Main) {
                    changeEnglishLanguage.postValue(it)
                }
            }
        }
    }


    override fun handleError(exception: Throwable) {
        TODO("Not yet implemented")
    }


}