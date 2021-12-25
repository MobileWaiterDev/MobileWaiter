package com.mwaiterdev.domain.usecase.tablesscreen

import com.mwaiterdev.domain.models.ITableItem

interface ITablesInteractor {
    /**
     * Получить список столов
     * @return List<ITableItem>
     */
    suspend fun getTables(): List<ITableItem>

    /**
     * Получить список залов
     * @return List<String>
     */
    suspend fun getTableGroups(): List<String>
}