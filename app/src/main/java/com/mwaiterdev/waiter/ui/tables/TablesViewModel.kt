package com.mwaiterdev.waiter.ui.tables

import com.mwaiterdev.domain.ScreenTablesState
import com.mwaiterdev.domain.repository.RepositoryImp
import kotlinx.coroutines.launch

class TablesViewModel(
    private val interactor: ITablesInteractor = TablesInteractorImpl(repository = RepositoryImp())
) : BaseTablesViewModel() {

    override fun getTables() {
        viewModelScopeCoroutine.launch {
            val result = interactor.getFilterTables()
            if (result.isNullOrEmpty().not()) {
                getTablesLiveData().postValue(ScreenTablesState.Success(result))
            }
        }
    }

    override fun getTableGroups() {
        viewModelScopeCoroutine.launch {
            val result = interactor.getTableGroups()
            if (result.isNullOrEmpty().not()) {
                getTableGroupsLiveData().postValue(ScreenTablesState.Success(result))
            }
        }
    }

    override fun handleError(throwable: Throwable) {
    }
}