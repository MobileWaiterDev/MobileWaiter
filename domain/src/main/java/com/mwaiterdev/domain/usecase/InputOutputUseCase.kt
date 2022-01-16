package com.mwaiterdev.domain.usecase

import kotlinx.coroutines.*

abstract class InputOutputUseCase<T, R>(
    dispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    private val job = Job()
    private val scope = CoroutineScope(dispatcher + job)

    abstract suspend fun process(arg: T): Result<R>

    suspend fun execute(
        arg: T,
        onSuccess: (Result.Success<R>) -> Unit,
        onError: (Result.Error) -> Unit) {
        val job = coroutineScope {
            async {
                process(arg)
            }
        }
        scope.launch {
            try {
                when (val result = job.await()) {
                    is Result.Success<R> -> onSuccess(result)
                    is Result.Error -> onError(result)
                    else -> {}
                }
            } catch (e: Exception) {
                onError(Result.Error(e, "An error has occurred during coroutine execution: ${e.message}"))
            }
        }
    }
}