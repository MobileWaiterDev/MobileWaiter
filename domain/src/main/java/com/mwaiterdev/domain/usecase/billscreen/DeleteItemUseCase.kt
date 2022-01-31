package com.mwaiterdev.domain.usecase.billscreen

import com.mwaiterdev.domain.repository.Repository

class DeleteItemUseCase(private val repository: Repository) {

    /**
     * Удалить товар из счета
     * @param billItemId Id товара в счете
     * @return Boolean
     */
    suspend fun execute(billItemId: Long): Boolean =
        repository.deleteItem(billItemId = billItemId).success
}