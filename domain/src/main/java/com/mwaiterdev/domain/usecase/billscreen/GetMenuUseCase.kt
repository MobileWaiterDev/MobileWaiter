package com.mwaiterdev.domain.usecase.billscreen

import com.mwaiterdev.domain.models.IMenuItem
import com.mwaiterdev.domain.models.Item
import com.mwaiterdev.domain.models.ItemGroup
import com.mwaiterdev.domain.models.Price
import com.mwaiterdev.domain.models.response.ItemGroups
import com.mwaiterdev.domain.repository.Repository

class GetMenuUseCase(private val repository: Repository) {

    /**
     * Получить список категорий/товаров меню
     * @param itemGroupId Id категории меню
     * @return ItemGroups
     */
    suspend fun execute(itemGroupId: Long): ItemGroups {
        if (itemGroupId > ZERO_VALUE) {
            val result = repository.getItemsById(itemGroupId = itemGroupId)
            if (result.success) {
                val items: ArrayList<IMenuItem> = arrayListOf()
                result.items?.forEach { item ->
                    items.add(
                        Item(
                            itemId = item.itemId,
                            itemGroupId = item.itemGroupId,
                            name = item.name,
                            shorName = item.shortName,
                            available = item.available,
                            price = Price(itemId = item.itemId, price = item.price),
                            favourite = item.favourite
                        )
                    )
                }
                return ItemGroups(items)
            } else {
                return ItemGroups(arrayListOf())
            }
        } else {
            val result = repository.getItemGroups()
            if (result.success) {
                val items: ArrayList<IMenuItem> = arrayListOf()
                result.itemGroups?.forEach { itemGroup ->
                    items.add(
                        ItemGroup(
                            itemGroupId = itemGroup.itemGroupId,
                            name = itemGroup.name,
                            shortName = itemGroup.shortName,
                            available = itemGroup.available
                        )
                    )
                }
                return ItemGroups(items)
            } else {
                return ItemGroups(arrayListOf())
            }
        }
    }

    companion object {
        private const val ZERO_VALUE = 0L
    }
}