package com.mwaiterdev.domain

sealed class ScreenState {
    data class Success(val result: Any?) : ScreenState()
    data class Error(val error: Throwable) : ScreenState()
    object Loading : ScreenState()
}