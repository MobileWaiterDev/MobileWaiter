package com.mwaiterdev.domain.usecase.billscreen

import com.mwaiterdev.domain.models.BillItem
import com.mwaiterdev.domain.models.Item
import com.mwaiterdev.domain.models.ItemGroup

interface IBillInteractor {

    /**
     * Получить список товаров в счете
     * @return List<BillItem>
     */
    suspend fun getBillItems(billId: Long): ArrayList<BillItem>

    /**
     * Получить список категорий меню
     * @return List<ItemGroup>
     */
    suspend fun getItemGroups(): ArrayList<ItemGroup>

    /**
     * Получить список товаров меню
     * @return List
     */
    suspend fun getItems(itemGroupId: Long): List<Item>
}