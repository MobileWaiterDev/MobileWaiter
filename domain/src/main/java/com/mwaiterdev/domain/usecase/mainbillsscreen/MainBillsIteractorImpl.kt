package com.mwaiterdev.domain.usecase.mainbillsscreen

import com.mwaiterdev.domain.models.response.BillsResponse
import com.mwaiterdev.domain.repository.Repository

class MainBillsIteractorImpl(
    private val repository: Repository
) : MainBillsIteractor {
    override suspend fun getFilterDataTableGroups(): BillsResponse {
        val rawData = repository.getHalls()
        rawData.tableGroups = rawData.tableGroups?.map { tableGroup ->
            tableGroup.tables = tableGroup.tables?.filter { table ->
                !table.bills.isNullOrEmpty()
            }
            tableGroup
        }
        return BillsResponse(
            "ok",
            true,
            rawData.tableGroups
        )

    }
}