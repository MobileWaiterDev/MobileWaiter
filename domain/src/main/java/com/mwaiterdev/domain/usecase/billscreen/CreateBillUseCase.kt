package com.mwaiterdev.domain.usecase.billscreen

import com.mwaiterdev.domain.models.response.NewBill
import com.mwaiterdev.domain.repository.Repository
import com.mwaiterdev.domain.usecase.InputOutputUseCase
import com.mwaiterdev.domain.usecase.Result

class CreateBillUseCase(private val repository: Repository): InputOutputUseCase<Long, NewBill>() {

    /**
     * Создать новый счет
     * @param tableId Id столика
     * @return NewBill
     */

    companion object {
        private const val ZERO_VALUE = 0L
    }

    override suspend fun process(arg: Long): Result<NewBill> {
        val result = repository.createBill(arg)
        return if (result.success) {
            Result.Success(NewBill(result.billId))
        } else {
            Result.Success(NewBill(ZERO_VALUE))
        }
    }
}