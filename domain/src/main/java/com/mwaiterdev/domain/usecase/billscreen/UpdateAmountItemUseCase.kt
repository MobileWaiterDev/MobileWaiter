package com.mwaiterdev.domain.usecase.billscreen

import com.mwaiterdev.domain.repository.Repository

class UpdateAmountItemUseCase(private val repository: Repository) {

    /**
     * Изменить количество товара в счете
     * @param billItemId Id товара в счете
     * @param amount Количество
     * @param price Цена
     * @return Boolean
     */
    suspend fun execute(billItemId: Long, amount: Float, price: Float): Boolean =
        repository.updateAmount(billItemId = billItemId, amount = amount, price = price).success
}