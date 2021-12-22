package com.mwaiterdev.waiter.ui.tables

import com.mwaiterdev.domain.ScreenState
import com.mwaiterdev.domain.repository.RepositoryImp
import com.mwaiterdev.domain.usecase.tablesscreen.ITablesInteractor
import com.mwaiterdev.domain.usecase.tablesscreen.TablesInteractorImpl
import kotlinx.coroutines.launch

class TablesViewModel(
    private val interactor: ITablesInteractor = TablesInteractorImpl(repository = RepositoryImp())
) : BaseTablesViewModel() {

    override fun getTables() {
        viewModelScopeCoroutine.launch {
            val result = interactor.getFilterTables()
            if (result.isNullOrEmpty().not()) {
                getTablesLiveData().postValue(ScreenState.Success(result))
            }
        }
    }

    override fun getTableGroups() {
        viewModelScopeCoroutine.launch {
            val result = interactor.getTableGroups()
            if (result.isNullOrEmpty().not()) {
                getTableGroupsLiveData().postValue(ScreenState.Success(result))
            }
        }
    }

    override fun handleError(throwable: Throwable) {
    }
}