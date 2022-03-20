package com.themoviedb.presentation.ui.home

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.themoviedb.domain.interactor.EventInteractor
import com.themoviedb.domain.interactor.MoveInteractor
import com.themoviedb.presentation.R
import com.themoviedb.presentation.ui.BaseViewModel
import com.themoviedb.presentation.ui.app.AppState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.UnknownHostException

class HomeViewModel(
    private val moveInteractor: MoveInteractor,
    private val eventInteractor: EventInteractor,
    private val context: Context
) :
    BaseViewModel() {

    private val liveDataToObservable: MutableLiveData<AppState> = MutableLiveData()
    private val isEnglishLanguage: MutableLiveData<Boolean> = MutableLiveData()

    fun getLiveData(): LiveData<AppState> = liveDataToObservable
    fun getIsEnglishLanguage(): LiveData<Boolean> = isEnglishLanguage

    var language: String = moveInteractor.getLanguage()

    fun getMoves() {
        liveDataToObservable.value = AppState.Loading
        coroutineScope.launch {
            val list = moveInteractor.getMoves(language)
            moveInteractor.saveMoves(list, language)
            withContext(Dispatchers.Main) {
                liveDataToObservable.postValue(
                    AppState.Success(
                        LocalListResponce(
                            list
                        )
                    )
                )
            }
        }
    }

    fun sendMessageSwitchLanguage(lang: String) {
        coroutineScope.launch()
        {
            eventInteractor.setSwitchLanguage(lang)
        }
    }

    override fun handleError(error: Throwable) {
        var message = error.message
        when (error) {
            is UnknownHostException -> {
                message = context.getString(R.string.internet_no_connection)
            }
        }

        liveDataToObservable.postValue(
            AppState.Error(
                Throwable("ERROR: $message")
            )
        )
        coroutineScope.launch {
            val list = moveInteractor.loadMoves()
            withContext(Dispatchers.Main) {
                liveDataToObservable.postValue(
                    AppState.Success(
                        LocalListResponce(
                            list
                        )
                    )
                )
            }
        }
    }

    fun clickSwitchMoves(checked: Boolean) {
        if (checked) {
            moveInteractor.setLanguage(context.getString(R.string.prefix_en))
            language = context.getString(R.string.prefix_en)
        } else {
            moveInteractor.setLanguage(context.getString(R.string.prefix_ru))
            language = context.getString(R.string.prefix_ru)
        }
        sendMessageSwitchLanguage(language)
        isEnglishLanguage.postValue(
            language.equals(context.getString(R.string.prefix_en))
        )

        getMoves()
    }

    init {
        isEnglishLanguage.postValue(
            moveInteractor.getLanguage().equals(context.getString(R.string.prefix_en))
        )
        getMoves()
    }
}