package com.mwaiterdev.domain.usecase.billscreen

import com.mwaiterdev.domain.models.CreateBillData
import com.mwaiterdev.domain.models.response.NewBill
import com.mwaiterdev.domain.repository.Repository
import com.mwaiterdev.domain.usecase.InputOutputUseCase
import com.mwaiterdev.domain.usecase.Result

class CreateBillUseCase(private val repository: Repository) :
    InputOutputUseCase<CreateBillData, NewBill>() {

    /**
     * Создать новый счет
     * @param arg Id столика и пользователя
     * @return NewBill
     */
    override suspend fun process(arg: CreateBillData): Result<NewBill> {
        val result = repository.createBill(arg.tableId, arg.userId)
        return if (result.success) {
            Result.Success(NewBill(result.billId))
        } else {
            Result.Success(NewBill(ZERO_VALUE))
        }
    }

    companion object {
        private const val ZERO_VALUE = 0L
    }
}