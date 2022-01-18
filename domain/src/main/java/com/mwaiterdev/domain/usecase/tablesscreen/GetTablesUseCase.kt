package com.mwaiterdev.domain.usecase.tablesscreen

import com.mwaiterdev.domain.models.ITableItem
import com.mwaiterdev.domain.models.TableGroupItem
import com.mwaiterdev.domain.models.TableItem
import com.mwaiterdev.domain.models.response.Tables
import com.mwaiterdev.domain.models.response.TablesResponse
import com.mwaiterdev.domain.repository.Repository
import com.mwaiterdev.domain.usecase.OutputUseCase
import com.mwaiterdev.domain.usecase.Result

class GetTablesUseCase(
    private val repository: Repository
): OutputUseCase<Tables>() {
    override suspend fun process(): Result<Tables> {
        val response = repository.getTables()
        val tables = convertTables(response)
        return Result.Success(Tables(tables))
    }

    private fun convertTables(response: TablesResponse): ArrayList<ITableItem> {
        val items: ArrayList<ITableItem> = arrayListOf()
        response.tableGroups?.forEach { groupTable ->
            items.add(
                TableGroupItem(
                    tableGroupId = groupTable.tableGroupId,
                    name = groupTable.name,
                    askCustomers = groupTable.askCustomers,
                    isExpanded = false
                )
            )
            groupTable.tables?.forEach { table ->
                items.add(
                    TableItem(
                        tableId = table.tableId,
                        tableGroupId = table.tableGroupId,
                        name = table.name,
                        state = table.state,
                        maxCustomers = table.maxCustomers,
                        askCustomers = table.askCustomers
                    )
                )
            }
        }
        return items
    }
}