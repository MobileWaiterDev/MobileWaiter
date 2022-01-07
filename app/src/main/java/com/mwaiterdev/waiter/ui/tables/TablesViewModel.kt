package com.mwaiterdev.waiter.ui.tables

import com.mwaiterdev.domain.ScreenState
import com.mwaiterdev.domain.usecase.tablesscreen.ITablesInteractor
import kotlinx.coroutines.launch

class TablesViewModel(
    private val interactor: ITablesInteractor
) : BaseTablesViewModel() {

    private fun getTables() =
        viewModelScopeCoroutine.launch {
            val result = interactor.getTables()
            if (result.data.isNullOrEmpty().not()) {
                getLiveData().postValue(ScreenState.Success(data = result))
            } else {
                getLiveData().postValue(ScreenState.Error(error = Exception(ERROR_MESSAGE)))
            }
        }

    private fun getTableGroups() =
        viewModelScopeCoroutine.launch {
            val result = interactor.getTableGroups()
            if (result.data.isNullOrEmpty().not()) {
                getLiveData().postValue(ScreenState.Success(data = result))
            } else {
                getLiveData().postValue(ScreenState.Error(error = Exception(ERROR_MESSAGE)))
            }
        }

    override fun handleError(throwable: Throwable) {
    }

    override fun getData() {
        viewModelScopeCoroutine.launch {
            getTableGroups().join()
            getTables()
        }
    }

    //ToDo Вынести в ресурсы и создать интерактор для получения данных с ресурсов
    companion object {
        const val ERROR_MESSAGE = "Ошибка при получении данных"
    }
}