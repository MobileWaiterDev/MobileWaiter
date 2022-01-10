package com.mwaiterdev.waiter.ui.bill

import android.util.Log
import com.mwaiterdev.domain.ScreenState
import com.mwaiterdev.domain.usecase.billscreen.IBillInteractor
import kotlinx.coroutines.launch

class BillViewModel(
    private val interactor: IBillInteractor
) : BaseBillViewModel() {

    override fun getBillItems(billId: Long) =
        viewModelScopeCoroutine.launch {
            billItemsLiveData().postValue(
                ScreenState.Loading
            )
            Log.d("WaiterDebug", "fun getBillItems($billId)")
            val billItems = interactor.getBillItemsById(billId = billId)
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

    companion object {
        //ToDo Вынести в ресурсы и создать интерактор для получения данных с ресурсов
        const val ERROR_MESSAGE = "Ошибка при выполнении операции..."
        const val ZERO_VALUE = 0L
    }
}