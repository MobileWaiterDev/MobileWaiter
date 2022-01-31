package com.mwaiterdev.domain.usecase

import kotlinx.coroutines.*

abstract class UseCase(
    dispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    private val job = Job()
    private val scope = CoroutineScope(dispatcher + job)

    abstract suspend fun process(): Result<Nothing>

    suspend fun execute(
        onCompleted: () -> Unit,
        onError: (Result.Error) -> Unit) {
        val job = coroutineScope {
            async {
                process()
            }
        }
        scope.launch {
            try {
                when (val result = job.await()) {
                    is Result.Completed -> onCompleted()
                    is Result.Error -> onError(result)
                    else -> {}
                }
            } catch (e: Exception) {
                onError(Result.Error(e, "An error has occurred during coroutine execution: ${e.message}"))
            }
        }
    }
}