package com.mwaiterdev.waiter.ui.bill

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mwaiterdev.domain.ScreenState
import kotlinx.coroutines.*

abstract class BaseBillViewModel : ViewModel() {
    private val billItemsLiveData = MutableLiveData<ScreenState>()
    private val menuLiveData = MutableLiveData<ScreenState>()
    private val billInfoLiveData = MutableLiveData<ScreenState>()
    private val newBillLiveData = MutableLiveData<ScreenState>()
    private val deleteBillLiveData = MutableLiveData<ScreenState>()
    private val sendCookBillLiveData = MutableLiveData<ScreenState>()
    private val deleteEmergencyLiveData = MutableLiveData<ScreenState>()
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
    fun billInfoLiveData() = billInfoLiveData
    fun newBillLiveData() = newBillLiveData
    fun deleteBillLiveData() = deleteBillLiveData
    fun sendCookBillLiveData() = sendCookBillLiveData
    fun deleteEmergencyLiveData() = deleteEmergencyLiveData

    abstract fun createBill(tableId: Long): Job
    abstract fun getBillInfo(): Job
    abstract fun getMenu(itemGroupId: Long = 0L): Job
    abstract fun getBillItems(needScrollToPosition: Boolean = false): Job
    abstract fun search(text: String): Job

    override fun onCleared() {
        super.onCleared()
        viewModelScopeCoroutine
            .coroutineContext
            .cancel()
    }
}