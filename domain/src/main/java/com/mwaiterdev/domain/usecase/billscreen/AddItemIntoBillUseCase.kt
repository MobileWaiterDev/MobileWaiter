package com.mwaiterdev.domain.usecase.billscreen

import com.mwaiterdev.domain.repository.Repository

class AddItemIntoBillUseCase(private val repository: Repository) {

    /**
     * Добавить товар в счет
     * @param billId Id счета
     * @param itemId Id товара
     * @param amount Количество
     * @param price Цена
     * @return Boolean
     */
    suspend fun execute(billId: Long, itemId: Long, amount: Float, price: Float): Boolean =
        repository.addItemIntoBill(
            billId = billId,
            itemId = itemId,
            amount = amount,
            price = price
        ).success
}