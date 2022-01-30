package com.mwaiterdev.domain.usecase.billscreen

import com.mwaiterdev.domain.repository.Repository

class PrintBillUseCase(private val repository: Repository) {

    /**
     * Печать счета
     * @param billId Id счета
     * @return Boolean
     */
    suspend fun execute(billId: Long): Boolean =
        repository.printBill(billId = billId).success
}