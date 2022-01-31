package com.mwaiterdev.waiter.ui.tables

import com.mwaiterdev.domain.ScreenState
import com.mwaiterdev.domain.usecase.tablesscreen.GetTablesUseCase
import kotlinx.coroutines.launch

class TablesViewModel(
    private val getTablesUseCase: GetTablesUseCase
) : BaseTablesViewModel() {

    private suspend fun getTables() =
        viewModelScopeCoroutine.launch {
            getTablesUseCase.execute(
                onSuccess = { getLiveData().postValue(ScreenState.Success(data = it.data)) },
                onError = {
                    getLiveData().postValue(
                        ScreenState.Error(
                            error = Exception(
                                ERROR_MESSAGE
                            )
                        )
                    )
                }
            )
        }

    override fun handleError(throwable: Throwable) {}

    override fun getData() {
        viewModelScopeCoroutine.launch {
            getTables()
        }
    }

    //ToDo Вынести в ресурсы и создать интерактор для получения данных с ресурсов
    companion object {
        const val ERROR_MESSAGE = "Ошибка при получении данных"
    }
}