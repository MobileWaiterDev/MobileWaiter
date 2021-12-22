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
                ScreenState.Success(interactor.getBillItems(0))
            )
        }
    }

    override fun getItemGroups() {
        viewModelScopeCoroutine.launch {
            getItemGroupListLiveData().postValue(
                ScreenState.Success(interactor.getItemGroups())
            )
        }
    }

    override fun getItems() {
        viewModelScopeCoroutine.launch {
            getItemListLiveData().postValue(
                ScreenState.Success(interactor.getItems())
            )
        }
    }

    override fun handleError(throwable: Throwable) {
    }
}