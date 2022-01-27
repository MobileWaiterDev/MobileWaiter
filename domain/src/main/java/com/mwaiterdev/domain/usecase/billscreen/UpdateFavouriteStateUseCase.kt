package com.mwaiterdev.domain.usecase.billscreen

import com.mwaiterdev.domain.repository.Repository

class UpdateFavouriteStateUseCase(private val repository: Repository) {

    /**
     * Изменить статус избранного у товара
     * @param favourite Значение избраного, 0/1
     * @param itemId Id товара
     * @return Boolean
     */
    suspend fun execute(currentFavouriteState: Int, itemId: Long): Boolean =
        if (currentFavouriteState == FAVOURITE_TRUE) {
            repository.updateFavouriteState(FAVOURITE_FALSE, itemId).success
        } else {
            repository.updateFavouriteState(FAVOURITE_TRUE, itemId).success
        }

    companion object {
        private const val FAVOURITE_TRUE = 1
        private const val FAVOURITE_FALSE = 0
    }

}