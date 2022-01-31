package com.mwaiterdev.domain.usecase.loginscreen

import com.mwaiterdev.domain.ScreenState
import com.mwaiterdev.domain.models.User
import com.mwaiterdev.domain.repository.LocalRepository
import com.mwaiterdev.domain.repository.Repository
import kotlinx.coroutines.delay

class LogInUseCase(
    private val repository: Repository,
    private val localRepository: LocalRepository
) {
    suspend fun execute(pin: String): ScreenState {
        delay(DELAY)
        val result = repository.getUserByPin(pin)

        return if (result.success) {
            val user: User = User(
                userId = result.user?.first()?.userId ?: 0,
                userGroupId = result.user?.first()?.userGroupId ?: 0,
                name = result.user?.first()?.name ?: "",
                isActive = result.user?.first()?.active ?: false,
                pin = result.user?.first()?.pin ?: "",
                groupName = result.user?.first()?.userGroup ?: ""
            )

            localRepository.saveUser(user)
            ScreenState.Success(user)
        } else {
            ScreenState.Error(Exception(result.message))
        }
    }

    companion object {
        private const val DELAY = 1200L
    }
}