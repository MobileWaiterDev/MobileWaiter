package com.mwaiterdev.domain.usecase.billscreen

import com.mwaiterdev.domain.repository.Repository

class SendCookItemsUseCase(private val repository: Repository) {

    /**
     * Готовить продукты
     * @param billId Id счета
     * @return Boolean
     */
    suspend fun execute(billId: Long): Boolean =
        repository.cookBill(billId = billId).success
}