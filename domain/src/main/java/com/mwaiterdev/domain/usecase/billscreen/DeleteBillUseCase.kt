package com.mwaiterdev.domain.usecase.billscreen

import com.mwaiterdev.domain.repository.Repository

class DeleteBillUseCase(private val repository: Repository) {

    /**
     * Удалить счет
     * @param billId Id счета
     * @return Boolean
     */
    suspend fun execute(billId: Long): Boolean =
        repository.deleteBill(billId = billId).success
}