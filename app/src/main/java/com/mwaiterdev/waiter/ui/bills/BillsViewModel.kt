package com.mwaiterdev.waiter.ui.bills

import com.mwaiterdev.domain.AppState
import com.mwaiterdev.domain.usecase.mainbillsscreen.MainBillsIteractor
import com.mwaiterdev.waiter.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class BillsViewModel(
    private val interactor: MainBillsIteractor
) : BaseViewModel() {

    override fun handleError(throwable: Throwable) {
        customLiveData.postValue(AppState.Error(throwable))
    }

    override fun getData() {
        viewModelScopeCoroutine.launch {
            customLiveData.postValue(
                AppState.Success(interactor.getFilterDataTableGroups())
            )
        }
    }

}