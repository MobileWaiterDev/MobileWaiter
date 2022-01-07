package com.mwaiterdev.domain.usecase.billscreen

import com.mwaiterdev.domain.models.response.BillItems
import com.mwaiterdev.domain.models.response.ItemGroups

interface IBillInteractor {
    /**
     * Получить список товаров в счете
     * @param billId Id счета
     * @return BillItems
     */
    suspend fun getBillItemsById(billId: Long): BillItems

    /**
     * Получить список категорий/товаров меню
     * @param itemGroupId Id категории меню
     * @return ItemGroups
     */
    suspend fun getMenu(itemGroupId: Long): ItemGroups
}