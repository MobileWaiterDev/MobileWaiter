package com.mwaiterdev.domain.repository.datasource

import com.mwaiterdev.domain.models.response.*

interface IRemoteDataSource {
    /**
     * Получить пользователя по пин-коду
     * @param pin Пин-код пользователя
     * @return UserResponse
     */
    suspend fun getUserByPin(pin: String): UserResponse

    /**
     * Получить все счета
     * @return BillsResponse
     */
    suspend fun getBills(): BillsResponse

    /**
     * Получить все столы
     * @return TablesResponse
     */
    suspend fun getTables(): TablesResponse

    /**
     * Получить список категорий товаров меню
     * @return ItemGroupsResponse
     */
    suspend fun getItemGroups(): ItemGroupsResponse

    /**
     * Получить список товаров меню
     * @param itemGroupId Id группы товаров
     * @return ItemsResponse
     */
    suspend fun getItemsById(itemGroupId: Long): ItemsResponse

    /**
     * Получить список товаров для счета
     * @param billId Id счета
     * @return BillItemsResponse
     */
    suspend fun getBillItemsById(billId: Long): BillItemsResponse

    /**
     * Получить список залов. (Для списка фильтрации)
     * @return TableGroupsResponse
     */
    suspend fun getTableGroups(): TableGroupsResponse

    /**
     * Создать новый счет
     * @param tableId Id столика
     * @return NewBillResponse
     */
    suspend fun createBill(tableId: Long): NewBillResponse

    /**
     * Информация о счете
     * @param billId Id счета
     * @return BillInfoResponse
     */
    suspend fun getBillInfo(billId: Long): BillsInfoResponse

    /**
     * Добавить товар в счет
     * @param billId Id счета
     * @param itemId Id товара
     * @param amount Количество
     * @param price Цена
     * @return OperationResult
     */
    suspend fun addItemIntoBill(
        billId: Long,
        itemId: Long,
        amount: Float,
        price: Float
    ): OperationResult
}