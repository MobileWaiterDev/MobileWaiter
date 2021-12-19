package com.mwaiterdev.waiter.ui.bills

import com.mwaiterdev.domain.AppState
import com.mwaiterdev.domain.repository.Repository
import com.mwaiterdev.domain.repository.RepositoryImp
import com.mwaiterdev.waiter.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class BillsViewModel(
    private val repository: Repository = RepositoryImp()
) : BaseViewModel() {

    override fun handleError(throwable: Throwable) {
        customLiveData.postValue(AppState.Error(throwable))
    }

    override fun getData() {
        viewModelScopeCoroutine.launch {
            customLiveData.postValue(
                AppState.Success(repository.getHalls())
            )
        }
    }

}