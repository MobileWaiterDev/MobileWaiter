package com.mwaiterdev.waiter.ui.bill

import android.util.Log
import com.mwaiterdev.domain.ScreenState
import com.mwaiterdev.domain.usecase.billscreen.IBillInteractor
import kotlinx.coroutines.launch

class BillViewModel(
    private val interactor: IBillInteractor
) : BaseBillViewModel() {

    private var currentBillId: Long = ZERO_VALUE

    fun setCurrentBill(billId: Long) {
        currentBillId = billId
    }

    override fun getBillItems(needScrollToPosition: Boolean) =
        viewModelScopeCoroutine.launch {
            billItemsLiveData().postValue(
                ScreenState.Loading
            )
            Log.d("WaiterDebug", "fun getBillItems($currentBillId)")
            val billItems =
                interactor.getBillItemsById(billId = currentBillId, needScrollToPosition)
            if (billItems.data.isNullOrEmpty().not()) {
                billItemsLiveData().postValue(
                    ScreenState.Success(data = billItems)
                )
            }
        }

    override fun getMenu(itemGroupId: Long) =
        viewModelScopeCoroutine.launch {
            menuLiveData().postValue(
                ScreenState.Loading
            )
            Log.d("WaiterDebug", "fun getItems($itemGroupId)")
            val result = interactor.getMenu(itemGroupId)
            if (result.data.isNullOrEmpty().not()) {
                menuLiveData().postValue(
                    ScreenState.Success(data = result)
                )
            } else {
                menuLiveData().postValue(
                    ScreenState.Error(error = Exception(ERROR_MESSAGE))
                )
            }
        }

    override fun createBill(tableId: Long) =
        viewModelScopeCoroutine.launch {
            operationLiveData().postValue(
                ScreenState.Loading
            )
            val newBill = interactor.createBill(tableId = tableId)
            if (newBill.data != ZERO_VALUE) {
                operationLiveData().postValue(
                    ScreenState.Success(data = newBill)
                )

            } else {
                operationLiveData().postValue(
                    ScreenState.Error(error = Exception(ERROR_MESSAGE))
                )
            }
        }

    override fun loadBill(billId: Long) =
        viewModelScopeCoroutine.launch {
            operationLiveData().postValue(
                ScreenState.Loading
            )
            val billInfo = interactor.getBillInfo(billId = billId)
            operationLiveData().postValue(
                ScreenState.Success(data = billInfo)
            )
        }

    override fun handleError(throwable: Throwable) {}

    fun addItemIntoBill(itemId: Long, amount: Float, price: Float) {
        viewModelScopeCoroutine.launch {
            interactor.addItemIntoBill(
                billId = currentBillId,
                itemId = itemId,
                amount = amount,
                price = price
            ).also {
                getBillItems(needScrollToPosition = true)
            }
        }
    }

    fun updateAmount(billItemId: Long, amount: Float, price: Float) {
        viewModelScopeCoroutine.launch {
            interactor.updateAmount(
                billItemId = billItemId,
                amount = amount,
                price = price
            ).also {
                getBillItems(needScrollToPosition = false)
            }
        }
    }

    fun deleteItem(billItemId: Long) {
        viewModelScopeCoroutine.launch {
            interactor.deleteItem(
                billItemId = billItemId,
            ).also {
                getBillItems(needScrollToPosition = false)
            }
        }
    }

    companion object {
        //ToDo Вынести в ресурсы и создать интерактор для получения данных с ресурсов
        const val ERROR_MESSAGE = "Ошибка при выполнении операции..."
        const val ZERO_VALUE = 0L
    }
}