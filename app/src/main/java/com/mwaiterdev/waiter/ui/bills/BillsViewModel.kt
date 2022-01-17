package com.mwaiterdev.waiter.ui.bills

import com.mwaiterdev.domain.AppState
import com.mwaiterdev.domain.usecase.mainbillsscreen.MainBillsIteractor
import com.mwaiterdev.utils.extensions.SharedPreferences.BillsLocalStorage
import com.mwaiterdev.waiter.ui.base.viemodel.BaseViewModel
import kotlinx.coroutines.launch

class BillsViewModel(
    private val interactor: MainBillsIteractor,
    private val preferences: BillsLocalStorage
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
            customLiveData.postValue(
                AppState.Success(interactor.getFilterDataTableGroups())
            )
        }
    }

}