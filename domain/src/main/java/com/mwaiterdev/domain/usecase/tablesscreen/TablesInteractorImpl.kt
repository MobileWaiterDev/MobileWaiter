package com.mwaiterdev.domain.usecase.tablesscreen

import com.mwaiterdev.domain.models.ITableItem
import com.mwaiterdev.domain.models.TableGroupItem
import com.mwaiterdev.domain.models.TableItem
import com.mwaiterdev.domain.repository.Repository

class TablesInteractorImpl(
    private val repository: Repository
) : ITablesInteractor {

    override suspend fun getTables(): List<ITableItem> {
        val result = repository.getTables()
        if (result.success && result.tableGroups.isNotEmpty()) {
            val items: ArrayList<ITableItem> = arrayListOf()
            result.tableGroups?.forEach { groupTable ->
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
        } else {
            return arrayListOf()
        }
    }

    override suspend fun getTableGroups(): List<String> {
        val result = repository.getTableGroups()
        return if (result.success && result.tableGroups.isNotEmpty()) {
            val items: ArrayList<String> = arrayListOf()
            result.tableGroups?.forEach { groupTable ->
                items.add(groupTable.name)
            }
            items
        } else {
            arrayListOf()
        }
    }
}