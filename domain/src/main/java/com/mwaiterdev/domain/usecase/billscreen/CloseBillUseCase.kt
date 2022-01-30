package com.mwaiterdev.domain.usecase.billscreen

import com.mwaiterdev.domain.repository.Repository

class CloseBillUseCase(private val repository: Repository) {

    /**
     * Закрыть счет
     * @param billId Id счета
     * @return Boolean
     */
    suspend fun execute(billId: Long): Boolean =
        repository.closeBill(billId = billId).success
}