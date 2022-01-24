package com.mwaiterdev.domain.usecase.mainbillsscreen

import com.mwaiterdev.domain.models.TableGroup
import com.mwaiterdev.domain.models.response.BillsResponse
import com.mwaiterdev.domain.repository.Repository
import com.mwaiterdev.domain.usecase.OutputUseCase
import com.mwaiterdev.domain.usecase.Result

class GetBillsUseCase(
    private val repository: Repository
) : OutputUseCase<List<TableGroup>?>() {

    private fun convertToListTableGroups(rawData: BillsResponse): List<TableGroup>? {
        rawData.tableGroups = rawData.tableGroups?.map { tableGroup ->
            tableGroup.tables = tableGroup.tables?.filter { table ->
                !table.bills.isNullOrEmpty()
            }
            tableGroup
        }
        val resp = rawData.tableGroups?.map {
            TableGroup(
                tableGroupId = it.tableGroupId,
                name = it.name,
                askCustomers = false,
                tables = it.tables
            )
        }

        return resp
    }

    override suspend fun process(): Result<List<TableGroup>?> {
        val rawData = repository.getHalls()
        val tablesGroups = convertToListTableGroups(rawData)
        return Result.Success<List<TableGroup>?>(tablesGroups)
    }

}