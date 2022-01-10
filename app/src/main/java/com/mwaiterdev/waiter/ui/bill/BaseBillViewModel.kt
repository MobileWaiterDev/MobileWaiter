package com.mwaiterdev.waiter.ui.bill

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mwaiterdev.domain.ScreenState
import kotlinx.coroutines.*

abstract class BaseBillViewModel : ViewModel() {
    private val billItemsLiveData = MutableLiveData<ScreenState>()
    private val menuLiveData = MutableLiveData<ScreenState>()
    private val operationLiveData = MutableLiveData<ScreenState>()
    protected val viewModelScopeCoroutine = CoroutineScope(
        Dispatchers.IO
                + SupervisorJob()
                + CoroutineExceptionHandler { _, throwable ->
            handleError(throwable)
        }
    )

    abstract fun handleError(throwable: Throwable): Any

    fun billItemsLiveData() = billItemsLiveData
    fun menuLiveData() = menuLiveData
    fun operationLiveData() = operationLiveData

    abstract fun createBill(tableId: Long): Job
    abstract fun loadBill(billId: Long): Job
    abstract fun getMenu(itemGroupId: Long = 0L): Job
    abstract fun getBillItems(): Job

    override fun onCleared() {
        super.onCleared()
        viewModelScopeCoroutine
            .coroutineContext
            .cancel()
    }
}