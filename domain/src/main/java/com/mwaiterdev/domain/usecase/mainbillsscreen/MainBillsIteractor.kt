package com.mwaiterdev.domain.usecase.mainbillsscreen

import com.mwaiterdev.domain.models.response.BillsResponse

interface MainBillsIteractor {
    suspend fun getFilterDataTableGroups(): BillsResponse
}