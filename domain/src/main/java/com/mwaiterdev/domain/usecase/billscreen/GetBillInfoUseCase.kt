package com.mwaiterdev.domain.usecase.billscreen

import com.mwaiterdev.domain.models.response.BillsInfo
import com.mwaiterdev.domain.repository.Repository

class GetBillInfoUseCase(private val repository: Repository) {

    /**
     * Информация о счете
     * @param billId Id счета
     * @return BillInfoResponse
     */
    suspend fun execute(billId: Long): BillsInfo =
        BillsInfo(repository.getBillInfo(billId = billId).billInfo)
}