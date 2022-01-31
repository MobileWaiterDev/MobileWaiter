package com.mwaiterdev.domain.usecase.tablesscreen

import com.mwaiterdev.domain.models.response.TableGroups
import com.mwaiterdev.domain.models.response.Tables

interface ITablesInteractor {
    /**
     * Получить список столов
     * @return Tables
     */
    suspend fun getTables(): Tables

    /**
     * Получить список залов
     * @return TableGroups
     */
    suspend fun getTableGroups(): TableGroups
}