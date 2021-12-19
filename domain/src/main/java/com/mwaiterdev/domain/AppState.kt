package com.mwaiterdev.domain

import com.mwaiterdev.domain.models.TableGroup

sealed class AppState {
    data class Success(val data: List<TableGroup>): AppState()
    data class Error(val error: Throwable): AppState()
    object Loading: AppState()
}
