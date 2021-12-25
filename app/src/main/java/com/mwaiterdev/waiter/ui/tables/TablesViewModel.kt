package com.mwaiterdev.waiter.ui.tables

import com.mwaiterdev.domain.ScreenState
import com.mwaiterdev.domain.repository.RepositoryMockImp
import com.mwaiterdev.domain.usecase.tablesscreen.ITablesInteractor
import com.mwaiterdev.domain.usecase.tablesscreen.TablesInteractorImpl
import kotlinx.coroutines.launch

class TablesViewModel(
    private val interactor: ITablesInteractor = TablesInteractorImpl(repository = RepositoryMockImp())
) : BaseTablesViewModel() {

    override fun getTables() {
        viewModelScopeCoroutine.launch {
            val result = interactor.getTables()
            if (result.isNullOrEmpty().not()) {
                getTablesLiveData().postValue(ScreenState.Success(result = result))
            } else {
                getTablesLiveData().postValue(ScreenState.Error(error = Exception(ERROR_MESSAGE)))
            }
        }
    }

    override fun getTableGroups() {
        viewModelScopeCoroutine.launch {
            val result = interactor.getTableGroups()
            if (result.isNullOrEmpty().not()) {
                getTableGroupsLiveData().postValue(ScreenState.Success(result = result))
            } else {
                getTableGroupsLiveData().postValue(ScreenState.Error(error = Exception(ERROR_MESSAGE)))
            }
        }
    }

    override fun handleError(throwable: Throwable) {
    }

    //ToDo Вынести в ресурсы и создать интерактор для получения данных с ресурсов
    companion object {
        const val ERROR_MESSAGE = "Ошибка при получении данных"
    }
}