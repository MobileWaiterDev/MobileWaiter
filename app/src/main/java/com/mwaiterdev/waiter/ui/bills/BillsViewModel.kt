package com.mwaiterdev.waiter.ui.bills

import com.mwaiterdev.domain.AppState
import com.mwaiterdev.domain.models.TableGroup
import com.mwaiterdev.domain.usecase.mainbillsscreen.InputUseCase
import com.mwaiterdev.utils.extensions.SharedPreferences.BillsLocalStorage
import com.mwaiterdev.waiter.ui.base.viemodel.BaseViewModel
import kotlinx.coroutines.launch

class BillsViewModel(
    private val interactor: com.mwaiterdev.domain.usecase.OutputUseCase<List<TableGroup>?>,
    private val preferences: BillsLocalStorage,
    private val filterUseCase: InputUseCase<String, List<TableGroup>?>,
    private val filterByUserIdUseCase: InputUseCase<Boolean, List<TableGroup>?>
) : BaseViewModel() {

    override fun handleError(throwable: Throwable) {
        customLiveData.postValue(AppState.Error(throwable))
    }

    fun initFilterData(): Boolean = preferences.isSwitchToMine
    fun setFilter(isSwitch: Boolean) = run { preferences.isSwitchToMine = isSwitch }
    fun initHallSwitcherItem(): Int = preferences.halsSwitcher
    fun setHallSwitcherItem(hall: Int) = run { preferences.halsSwitcher = hall }
    override fun getData() {
        viewModelScopeCoroutine.launch {
            interactor.execute(
                onSuccess = { getLiveData().postValue(AppState.Success(it.data)) },
                onError = { getLiveData().postValue(AppState.Error(it.error))}
            )
        }
    }

    fun filterBillsByHall(name: String, data: List<TableGroup>?): List<TableGroup>? =
        filterUseCase.execute(name, data)

    fun filterByUserId(isCheck: Boolean, data: List<TableGroup>?): List<TableGroup>? =
        filterByUserIdUseCase.execute(isCheck, data)

}