package com.mwaiterdev.waiter.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mwaiterdev.domain.ScreenState
import kotlinx.coroutines.*

abstract class BaseLoginViewModel : ViewModel() {
    protected val customLiveData = MutableLiveData<ScreenState>()
    protected val viewModelScopeCoroutine = CoroutineScope(
        Dispatchers.IO
                + SupervisorJob()
                + CoroutineExceptionHandler { _, throwable ->
            handleError(throwable)
        }
    )

    abstract fun handleError(throwable: Throwable)

    abstract fun logIn(pin: String): Job
    fun getLiveData() = customLiveData

    override fun onCleared() {
        super.onCleared()
        viewModelScopeCoroutine
            .coroutineContext
            .cancel()
    }
}