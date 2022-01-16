package com.mwaiterdev.domain.usecase.billscreen

import com.mwaiterdev.domain.models.response.NewBill
import com.mwaiterdev.domain.repository.Repository

class CreateBillUseCase(private val repository: Repository) {

    /**
     * Создать новый счет
     * @param tableId Id столика
     * @return NewBill
     */
    suspend fun execute(tableId: Long): NewBill {
        val result = repository.createBill(tableId)
        return if (result.success) {
            NewBill(result.billId)
        } else {
            NewBill(ZERO_VALUE)
        }
    }

    companion object {
        private const val ZERO_VALUE = 0L
    }
}