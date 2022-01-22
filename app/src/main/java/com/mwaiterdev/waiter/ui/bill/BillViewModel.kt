package com.mwaiterdev.waiter.ui.bill

import android.util.Log
import com.mwaiterdev.domain.ScreenState
import com.mwaiterdev.domain.usecase.billscreen.*
import kotlinx.coroutines.launch

class BillViewModel(
    private val getBillItemsUseCase: GetBillItemsUseCase,
    private val getMenuUseCase: GetMenuUseCase,
    private val createBillUseCase: CreateBillUseCase,
    private val getBillInfoUseCase: GetBillInfoUseCase,
    private val addItemIntoBillUseCase: AddItemIntoBillUseCase,
    private val updateAmountItemUseCase: UpdateAmountItemUseCase,
    private val deleteItemUseCase: DeleteItemUseCase
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
                getBillItemsUseCase.execute(billId = currentBillId, needScrollToPosition)
            if (billItems.data.isNullOrEmpty().not()) {
                billItemsLiveData().postValue(
                    ScreenState.Success(data = billItems)
                )
            } else {
                billItemsLiveData().postValue(
                    ScreenState.Error(error = Exception(ERROR_NO_DATA))
                )
            }
        }

    override fun getMenu(itemGroupId: Long) =
        viewModelScopeCoroutine.launch {
            menuLiveData().postValue(
                ScreenState.Loading
            )
            Log.d("WaiterDebug", "fun getItems($itemGroupId)")
            val result = getMenuUseCase.execute(itemGroupId)
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
            createBillUseCase.execute(
                arg = tableId,
                onSuccess = {operationLiveData().postValue(ScreenState.Success(data = it.data))},
                onError = {operationLiveData().postValue(
                    ScreenState.Error(error = Exception(ERROR_MESSAGE))
                )})

        }

    override fun loadBill(billId: Long) =
        viewModelScopeCoroutine.launch {
            operationLiveData().postValue(
                ScreenState.Loading
            )
            val billInfo = getBillInfoUseCase.execute(billId = billId)
            operationLiveData().postValue(
                ScreenState.Success(data = billInfo)
            )
        }

    override fun handleError(throwable: Throwable) {}

    fun addItemIntoBill(itemId: Long, amount: Float, price: Float) {
        viewModelScopeCoroutine.launch {
            addItemIntoBillUseCase.execute(
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
            updateAmountItemUseCase.execute(
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
            deleteItemUseCase.execute(
                billItemId = billItemId,
            ).also {
                getBillItems(needScrollToPosition = false)
            }
        }
    }

    companion object {
        //ToDo Вынести в ресурсы и создать интерактор для получения данных с ресурсов
        const val ERROR_MESSAGE = "Ошибка при выполнении операции..."
        const val ERROR_NO_DATA = "Отсутствуют данные..."
        const val ZERO_VALUE = 0L
    }
}