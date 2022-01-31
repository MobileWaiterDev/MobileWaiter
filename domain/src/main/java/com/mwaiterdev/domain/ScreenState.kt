package com.mwaiterdev.domain

import com.mwaiterdev.domain.models.response.IResponseResult

sealed class ScreenState {
    data class Success(val data: IResponseResult) : ScreenState()
    data class Error(val error: Throwable) : ScreenState()
    object Loading : ScreenState()
}