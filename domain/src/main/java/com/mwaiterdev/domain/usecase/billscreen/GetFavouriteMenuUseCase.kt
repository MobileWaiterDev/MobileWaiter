package com.mwaiterdev.domain.usecase.billscreen

import com.mwaiterdev.domain.models.IMenuItem
import com.mwaiterdev.domain.models.Item
import com.mwaiterdev.domain.models.Price
import com.mwaiterdev.domain.models.response.ItemGroups
import com.mwaiterdev.domain.repository.Repository

class GetFavouriteMenuUseCase(private val repository: Repository) {

    /**
     * Получить список избранных товаров
     * @return ItemGroups
     */
    suspend fun execute(): ItemGroups {
        val result = repository.getItemsById()
        if (result.success) {
            val items: ArrayList<IMenuItem> = arrayListOf()
            result.items?.forEach { item ->
                if (item.favourite == FAVOURITE_TRUE)
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
    }

    companion object {
        private const val FAVOURITE_TRUE = 1
    }
}