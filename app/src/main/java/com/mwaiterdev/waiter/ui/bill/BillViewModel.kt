package com.mwaiterdev.waiter.ui.bill

import com.mwaiterdev.domain.ScreenState
import com.mwaiterdev.domain.repository.RepositoryImp
import com.mwaiterdev.domain.usecase.billscreen.BillInteractorImpl
import com.mwaiterdev.domain.usecase.billscreen.IBillInteractor
import kotlinx.coroutines.launch

class BillViewModel(
    private val interactor: IBillInteractor = BillInteractorImpl(repository = RepositoryImp())
) : BaseBillViewModel() {

    override fun getBillItems() {
        viewModelScopeCoroutine.launch {
            getBillItemLiveData().postValue(
                ScreenState.Success(result = interactor.getBillItems(billId = 0))
            )
        }
    }

    override fun getItemGroups() {
        viewModelScopeCoroutine.launch {
            getItemGroupListLiveData().postValue(
                ScreenState.Success(result = interactor.getItemGroups())
            )
        }
    }

    override fun getItems(itemGroupId: Long) {
        viewModelScopeCoroutine.launch {
            getItemListLiveData().postValue(
                ScreenState.Success(result = interactor.getItems(itemGroupId))
            )
        }
    }

    override fun handleError(throwable: Throwable) {
    }
}