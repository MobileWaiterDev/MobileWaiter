package com.mwaiterdev.domain.usecase

sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val error: Throwable, val message: String) : Result<Nothing>()
    object Completed: Result<Nothing>()
}