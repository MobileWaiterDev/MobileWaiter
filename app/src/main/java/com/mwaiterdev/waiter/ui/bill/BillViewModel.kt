package com.mwaiterdev.waiter.ui.bill

import com.mwaiterdev.domain.ScreenState
import com.mwaiterdev.domain.usecase.billscreen.IBillInteractor
import kotlinx.coroutines.launch

class BillViewModel(
    private val interactor: IBillInteractor
) : BaseBillViewModel() {

    private fun getBillItems(billId: Long) =
        viewModelScopeCoroutine.launch {
            val billItems = interactor.getBillItemsById(billId = billId)
            if (billItems.data.isNullOrEmpty().not()) {
                getLiveData().postValue(
                    ScreenState.Success(data = billItems)
                )
            } else {
                getLiveData().postValue(
                    ScreenState.Error(error = Exception(ERROR_MESSAGE))
                )
            }
        }

    fun getItems(itemGroupId: Long = ZERO_VALUE) =
        viewModelScopeCoroutine.launch {
            val result = interactor.getMenu(itemGroupId)
            if (result.data.isNullOrEmpty().not()) {
                getLiveData().postValue(
                    ScreenState.Success(data = result)
                )
            } else {
                getLiveData().postValue(
                    ScreenState.Error(error = Exception(ERROR_MESSAGE))
                )
            }
        }

    override fun handleError(throwable: Throwable) {
        getLiveData().postValue(
            ScreenState.Error(error = throwable)
        )
    }

    fun loadBill(billId: Long) {
        /* Если счет новый*/
        if (billId == ZERO_VALUE) {
            viewModelScopeCoroutine.launch {
                getItems()
            }
        } else {
            viewModelScopeCoroutine.launch {
                getBillItems(billId).join()
                getItems()
            }
        }
    }

    override fun getData() {}

    companion object {
        //ToDo Вынести в ресурсы и создать интерактор для получения данных с ресурсов
        const val ERROR_MESSAGE = "Ошибка при получении данных"
        const val ZERO_VALUE = 0L
    }
}