package com.mwaiterdev.waiter.ui.bill

import com.mwaiterdev.domain.ScreenState
import com.mwaiterdev.domain.repository.RepositoryMockImp
import com.mwaiterdev.domain.usecase.billscreen.BillInteractorImpl
import com.mwaiterdev.domain.usecase.billscreen.IBillInteractor
import kotlinx.coroutines.launch

class BillViewModel(
    private val interactor: IBillInteractor =
        BillInteractorImpl(repository = RepositoryMockImp())
) : BaseBillViewModel() {

    override fun getBillItems() {
        viewModelScopeCoroutine.launch {
            val result = interactor.getBillItemsById(billId = SIMPLE_BILL_ID)
            if (result.isNullOrEmpty().not()) {
                getBillItemLiveData().postValue(
                    ScreenState.Success(result = result)
                )
            } else {
                getBillItemLiveData().postValue(
                    ScreenState.Error(error = Exception(ERROR_MESSAGE))
                )
            }
        }
    }

    override fun getItemGroups() {
        viewModelScopeCoroutine.launch {
            val result = interactor.getItemGroups()
            if (result.isNullOrEmpty().not()) {
                getItemGroupListLiveData().postValue(
                    ScreenState.Success(result = result)
                )
            } else {
                getItemGroupListLiveData().postValue(
                    ScreenState.Error(error = Exception(ERROR_MESSAGE))
                )
            }
        }
    }

    override fun getItems(itemGroupId: Long) {
        viewModelScopeCoroutine.launch {
            val result = interactor.getItemsById(itemGroupId)
            if (result.isNullOrEmpty().not()) {
                getItemListLiveData().postValue(
                    ScreenState.Success(result = result)
                )
            } else {
                getItemListLiveData().postValue(
                    ScreenState.Error(error = Exception(ERROR_MESSAGE))
                )
            }
        }
    }

    override fun handleError(throwable: Throwable) {
    }

    companion object {
        //ToDo Вынести в ресурсы и создать интерактор для получения данных с ресурсов
        const val ERROR_MESSAGE = "Ошибка при получении данных"

        //ToDo Организовать передачу billId во фрагмент счета, если счет не создается, а открывается
        const val SIMPLE_BILL_ID = 1L
    }
}