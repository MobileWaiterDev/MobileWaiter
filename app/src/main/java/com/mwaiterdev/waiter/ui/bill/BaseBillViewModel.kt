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
    private val printBillLiveData = MutableLiveData<ScreenState>()
    private val closeBillLiveData = MutableLiveData<ScreenState>()
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
    fun printBillLiveData() = printBillLiveData
    fun closeBillLiveData() = closeBillLiveData

    abstract fun createBill(tableId: Long): Job
    abstract fun getBillInfo(): Job
    abstract fun getMenu(itemGroupId: Long = 0L): Job
    abstract fun getBillItems(needScrollToPosition: Boolean = false): Job
    abstract fun search(text: String): Job
    abstract fun addItemIntoBill(itemId: Long, amount: Float, price: Float): Job
    abstract fun updateAmount(billItemId: Long, amount: Float, price: Float): Job
    abstract fun deleteItem(billItemId: Long): Job
    abstract fun deleteBill(): Job
    abstract fun getFavouriteMenu(): Job
    abstract fun sendCookBill(): Job
    abstract fun deleteEmergencyItem(billItemId: Long): Job

    abstract fun printBill(): Job
    abstract fun closeBill(): Job

    override fun onCleared() {
        super.onCleared()
        viewModelScopeCoroutine
            .coroutineContext
            .cancel()
    }
}