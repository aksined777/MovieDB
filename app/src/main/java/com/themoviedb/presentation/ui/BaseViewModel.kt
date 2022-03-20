package com.themoviedb.presentation.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import com.themoviedb.presentation.R
import kotlinx.coroutines.*
import java.net.UnknownHostException
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel() : ViewModel() {

    private val exHandler = CoroutineExceptionHandler { _, exception ->
        handleError(exception)
    }


abstract fun handleError(exception: Throwable)

private val job = SupervisorJob()
private val coroutineContext: CoroutineContext = Dispatchers.IO + exHandler + job
val coroutineScope = CoroutineScope(coroutineContext)


override fun onCleared() {
    coroutineContext.cancel()
    super.onCleared()
}

}