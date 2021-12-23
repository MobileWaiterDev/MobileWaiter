package com.mwaiterdev.waiter.ui.bill

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mwaiterdev.domain.ScreenState
import kotlinx.coroutines.*

abstract class BaseBillViewModel : ViewModel() {

    private val billItemLiveData = MutableLiveData<ScreenState>()
    private val itemListLiveData = MutableLiveData<ScreenState>()
    private val itemGroupListLiveData = MutableLiveData<ScreenState>()
    private val operationLiveData = MutableLiveData<Boolean>()

    protected val viewModelScopeCoroutine = CoroutineScope(
        Dispatchers.IO
                + SupervisorJob()
                + CoroutineExceptionHandler { _, throwable ->
            handleError(throwable)
        }
    )

    abstract fun getBillItems()
    abstract fun getItemGroups()
    abstract fun getItems(itemGroupId: Long)

    fun getBillItemLiveData() = billItemLiveData
    fun getItemListLiveData() = itemListLiveData
    fun getItemGroupListLiveData() = itemGroupListLiveData
    fun getOperationLiveData() = operationLiveData

    override fun onCleared() {
        super.onCleared()
        viewModelScopeCoroutine
            .coroutineContext
            .cancel()
    }

    abstract fun handleError(throwable: Throwable)
}