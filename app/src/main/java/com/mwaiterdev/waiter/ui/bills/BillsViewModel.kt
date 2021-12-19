package com.mwaiterdev.waiter.ui.bills

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mwaiterdev.domain.AppState
import com.mwaiterdev.domain.repository.Repository
import com.mwaiterdev.domain.repository.RepositoryImp
import kotlinx.coroutines.*

class BillsViewModel(
    private val repository: Repository = RepositoryImp()
) : ViewModel() {
    private val liveData = MutableLiveData<AppState>()
    private val viewModelScopeCoroutine = CoroutineScope(
        Dispatchers.IO
                + SupervisorJob()
                + CoroutineExceptionHandler { _, throwable ->
            handleError(throwable)
        }
    )

    private fun handleError(throwable: Throwable) {
        liveData.postValue(AppState.Error(throwable))
    }

    fun getLiveData() = liveData
    fun gedData() {
        viewModelScopeCoroutine.launch {
            liveData.postValue(
                AppState.Success(repository.getHalls())
            )
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScopeCoroutine
            .coroutineContext
            .cancel()
    }
}