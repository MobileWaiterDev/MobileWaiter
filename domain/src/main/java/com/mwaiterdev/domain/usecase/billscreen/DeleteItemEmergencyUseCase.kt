package com.mwaiterdev.domain.usecase.billscreen

import com.mwaiterdev.domain.repository.Repository

class DeleteItemEmergencyUseCase(private val repository: Repository) {

    /**
     * Отменить готовку уже распечатанного товара
     * @param billItemId Id товара
     * @return Boolean
     */
    suspend fun execute(billItemId: Long): Boolean =
        repository.emergencyCancel(billItemId).success
}