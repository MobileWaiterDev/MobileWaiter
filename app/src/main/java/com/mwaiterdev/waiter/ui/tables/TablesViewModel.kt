package com.mwaiterdev.waiter.ui.tables

import com.mwaiterdev.domain.ScreenTablesState
import com.mwaiterdev.domain.repository.Repository
import com.mwaiterdev.domain.repository.RepositoryImp
import kotlinx.coroutines.launch

class TablesViewModel(
    private val repository: Repository = RepositoryImp()
) : BaseTablesViewModel() {

    override fun getTables() {
    }

    override fun getTableGroups() {
        getTableGroupsLiveData().postValue(ScreenTablesState.Loading)

        viewModelScopeCoroutine.launch {
            val result = repository.getTableGroups()
            if (result.isNullOrEmpty().not()) {
                getTableGroupsLiveData().postValue(ScreenTablesState.Success(result))
            }
        }
    }

    override fun handleError(throwable: Throwable) {
    }

}