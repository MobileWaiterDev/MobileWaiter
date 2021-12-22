package com.mwaiterdev.domain

sealed class ScreenTablesState {
    data class Success(val result: Any?) : ScreenTablesState()
    data class Error(val error: Throwable) : ScreenTablesState()
    object Loading : ScreenTablesState()
}