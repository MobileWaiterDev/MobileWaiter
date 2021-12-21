package com.mwaiterdev.waiter.ui.tables

import com.mwaiterdev.domain.models.ITableItem

interface ITablesInteractor {

    /**
     * Получить список счетов
     * @return BillsAppState
     */
    suspend fun getFilterTables(tablesName: String = ""): List<ITableItem>

    /**
     * Получить список залов
     * @return List
     */
    suspend fun getTableGroups(): List<String>
}