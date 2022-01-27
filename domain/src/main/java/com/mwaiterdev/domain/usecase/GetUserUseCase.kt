package com.mwaiterdev.domain.usecase

import com.mwaiterdev.domain.models.User
import com.mwaiterdev.domain.repository.LocalRepository

class GetUserUseCase(private val localRepository: LocalRepository) {

    suspend fun execute(): User? =
        localRepository.getCurrentUser()
}