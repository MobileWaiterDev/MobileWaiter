package com.mwaiterdev.domain.usecase.billscreen

import com.mwaiterdev.domain.models.IMenuItem
import com.mwaiterdev.domain.models.Item
import com.mwaiterdev.domain.models.Price
import com.mwaiterdev.domain.models.response.ItemGroups
import com.mwaiterdev.domain.repository.Repository

class SearchItemUseCase(private val repository: Repository) {

    /**
     * Поиск товара по имени
     * @param text Текст для поиска
     * @return ItemsResponse
     */
    suspend fun execute(text: String): ItemGroups {
        val result = repository.search(text = text)
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
                        favourite = item.favourite,
                        bgColor = item.bgColor,
                        textColor = item.textColor
                    )
                )
            }
            return ItemGroups(items)
        } else {
            return ItemGroups(arrayListOf())
        }
    }

    companion object {
        private const val ZERO_VALUE = 0L
    }
}