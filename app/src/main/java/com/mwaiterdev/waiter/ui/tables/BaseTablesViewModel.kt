package com.mwaiterdev.waiter.ui.tables

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mwaiterdev.domain.ScreenTablesState
import kotlinx.coroutines.*

abstract class BaseTablesViewModel : ViewModel() {
    private val tableListLiveData = MutableLiveData<ScreenTablesState>()
    private val tableGroupListLiveData = MutableLiveData<ScreenTablesState>()
    protected val viewModelScopeCoroutine = CoroutineScope(
        Dispatchers.IO
                + SupervisorJob()
                + CoroutineExceptionHandler { _, throwable ->
            handleError(throwable)
        }
    )

    abstract fun getTables()
    abstract fun getTableGroups()

    fun getTablesLiveData() = tableListLiveData
    fun getTableGroupsLiveData() = tableGroupListLiveData

    override fun onCleared() {
        super.onCleared()
        viewModelScopeCoroutine
            .coroutineContext
            .cancel()
    }

    abstract fun handleError(throwable: Throwable)
}