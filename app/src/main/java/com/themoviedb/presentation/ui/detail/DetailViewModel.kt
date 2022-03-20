package com.themoviedb.presentation.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.themoviedb.domain.interactor.DetailInteractor
import com.themoviedb.presentation.ui.BaseViewModel
import com.themoviedb.presentation.ui.app.AppState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailViewModel(private val detailInteractor: DetailInteractor) : BaseViewModel() {

    private val liveDataToObservable: MutableLiveData<AppState> = MutableLiveData()

    fun getLiveData(): LiveData<AppState> = liveDataToObservable

    fun getMove(id: Long) {
        coroutineScope.launch {
            val move = detailInteractor.getMove(id)
            withContext(Dispatchers.Main) {
                liveDataToObservable.postValue(
                    AppState.Success(
                        LocalResponce(
                            move
                        )
                    )
                )
            }
        }
    }

    override fun handleError(exception: Throwable) {
        TODO("Not yet implemented")
    }
}