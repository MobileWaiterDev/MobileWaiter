package com.mwaiterdev.waiter.ui.tables

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mwaiterdev.domain.ScreenState
import kotlinx.coroutines.*

abstract class BaseTablesViewModel : ViewModel() {
    private val customLiveData = MutableLiveData<ScreenState>()
    protected val viewModelScopeCoroutine = CoroutineScope(
        Dispatchers.IO
                + SupervisorJob()
                + CoroutineExceptionHandler { _, throwable ->
            handleError(throwable)
        }
    )

    abstract fun handleError(throwable: Throwable): Any

    abstract fun getData()
    fun getLiveData() = customLiveData

    override fun onCleared() {
        super.onCleared()
        viewModelScopeCoroutine
            .coroutineContext
            .cancel()
    }
}