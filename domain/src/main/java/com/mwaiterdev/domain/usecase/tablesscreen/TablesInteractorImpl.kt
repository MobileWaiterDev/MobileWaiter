package com.mwaiterdev.domain.usecase.tablesscreen

import com.mwaiterdev.domain.models.ITableItem
import com.mwaiterdev.domain.models.TableGroupItem
import com.mwaiterdev.domain.models.TableItem
import com.mwaiterdev.domain.repository.Repository

class TablesInteractorImpl(
    private val repository: Repository
) : ITablesInteractor {

    override suspend fun getFilterTables(tablesName: String): List<ITableItem> {
        val result = repository.getTableGroupsWithTables()
        val items: ArrayList<ITableItem> = arrayListOf()
        result.forEach { groupTable ->
            items.add(
                TableGroupItem(
                    tableGroupId = groupTable.tableGroupId,
                    name = groupTable.name,
                    askCustomers = groupTable.askCustomers,
                    isExpanded = groupTable.isExpanded
                )
            )
            groupTable.tables.forEach { table ->
                items.add(
                    TableItem(
                        tableId = table.tableId,
                        tableGroupId = table.tableGroupId,
                        name = table.name,
                        state = table.state,
                        askCustomers = table.askCustomers,
                        maxCustomers = table.maxCustomers
                    )
                )
            }
        }
        return items
    }

    override suspend fun getTableGroups(): List<String> =
        repository.getTableGroups()
}