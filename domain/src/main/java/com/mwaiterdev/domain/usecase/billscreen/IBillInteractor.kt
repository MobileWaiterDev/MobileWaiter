package com.mwaiterdev.domain.usecase.billscreen

import com.mwaiterdev.domain.models.BillItem
import com.mwaiterdev.domain.models.ITableItem
import com.mwaiterdev.domain.models.Item
import com.mwaiterdev.domain.models.ItemGroup

interface IBillInteractor {

    /**
     * Получить список товаров в счете
     * @return List<BillItem>
     */
    suspend fun getBillItems(billId: Long): List<BillItem>

    /**
     * Получить список категорий меню
     * @return List<ItemGroup>
     */
    suspend fun getItemGroups(): List<ItemGroup>

    /**
     * Получить список товаров меню
     * @return List
     */
    suspend fun getItems(): List<Item>
}