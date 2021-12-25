package com.mwaiterdev.domain.usecase.billscreen

import com.mwaiterdev.domain.models.BillItem
import com.mwaiterdev.domain.models.Item
import com.mwaiterdev.domain.models.ItemGroup

interface IBillInteractor {

    /**
     * Получить список товаров в счете
     * @return List<BillItem>
     */
    suspend fun getBillItemsById(billId: Long): List<BillItem>

    /**
     * Получить список категорий меню
     * @return ItemGroupsResponse
     */
    suspend fun getItemGroups(): List<ItemGroup>

    /**
     * Получить список товаров меню
     * @return List
     */
    suspend fun getItemsById(itemGroupId: Long): List<Item>
}