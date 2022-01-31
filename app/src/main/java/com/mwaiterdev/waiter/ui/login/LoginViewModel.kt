package com.mwaiterdev.waiter.ui.login

import com.mwaiterdev.domain.ScreenState
import com.mwaiterdev.domain.usecase.loginscreen.LogInUseCase
import kotlinx.coroutines.launch

class LoginViewModel(
    private val logInUseCase: LogInUseCase
) : BaseLoginViewModel() {

    override fun handleError(throwable: Throwable) {
    }

    override fun logIn(pin: String) =
        viewModelScopeCoroutine.launch {
            customLiveData.postValue(ScreenState.Loading)
            customLiveData.postValue(
                logInUseCase.execute(pin)
            )
        }
}