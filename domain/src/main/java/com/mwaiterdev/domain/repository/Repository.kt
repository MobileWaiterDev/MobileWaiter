package com.mwaiterdev.domain.repository

import com.mwaiterdev.domain.models.*

interface Repository {
    suspend fun getWaitress(id: Int): User
    suspend fun getHalls(): List<TableGroup>

    /**
     * Получить список залов
     * @return List
     */
    suspend fun getTableGroups(): List<String>

    /**
     * Получить список столов для всех залов
     * @return List
     */
    suspend fun getTableGroupsWithTables(): List<TableGroup>

    /**
     * Получить список товаров для счета
     * @return List
     */
    suspend fun getBillItems(billId: Long): ArrayList<BillItem>

    /**
     * Получить список категорий товаров меню
     * @return List
     */
    suspend fun getItemGroups(): ArrayList<ItemGroup>

    /**
     * Получить список товаров меню
     * @return List
     */
    suspend fun getItems(itemGroupId: Long): List<Item>
}