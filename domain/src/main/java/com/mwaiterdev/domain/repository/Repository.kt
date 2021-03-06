package com.mwaiterdev.domain.repository

import com.mwaiterdev.domain.models.TableGroup
import com.mwaiterdev.domain.models.User
import com.mwaiterdev.domain.models.response.*

interface Repository {
    suspend fun getWaitress(id: Int): User
    suspend fun getHalls(): BillsResponse

    /**
     * Получить список залов. (Для списка фильтрации)
     * @return TableGroupsResponse
     */
    suspend fun getTableGroups(): TableGroupsResponse

    /**
     * Получить список столов для всех залов
     * @return List
     */
    suspend fun getTableGroupsWithTables(): List<TableGroup>

    /**
     * Получить список товаров для счета
     * @param billId Id счета
     * @return BillItemsResponse
     */
    suspend fun getBillItemsById(billId: Long): BillItemsResponse

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
    suspend fun getItemsById(itemGroupId: Long = 0L): ItemsResponse

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
     * Создать новый счет
     * @param tableId Id столика
     * @param userId Id пользователя
     * @return NewBillResponse
     */
    suspend fun createBill(tableId: Long, userId: Long): NewBillResponse

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

    /**
     * Изменить количество товара в счете
     * @param billItemId Id товара в счете
     * @param amount Количество
     * @param price Цена
     * @return OperationResult
     */
    suspend fun updateAmount(
        billItemId: Long,
        amount: Float,
        price: Float
    ): OperationResult

    /**
     * Удалить товар из счета
     * @param billItemId Id товара в счете
     * @return OperationResult
     */
    suspend fun deleteItem(
        billItemId: Long,
    ): OperationResult

    /**
     * Поиск товара по имени
     * @param text Текст для поиска
     * @return ItemsResponse
     */
    suspend fun search(text: String): ItemsResponse

    /**
     * Изменить статус избранного у товара
     * @param favourite Значение избраного, 0/1
     * @param itemId Id товара
     * @return OperationResult
     */
    suspend fun updateFavouriteState(
        favourite: Int,
        itemId: Long
    ): OperationResult

    /**
     * Удалить счет
     * @param billId Id счета
     * @return OperationResult
     */
    suspend fun deleteBill(
        billId: Long
    ): OperationResult


    /**
     * Готовить продукты
     * @param billId Id счета
     * @return OperationResult
     */
    suspend fun cookBill(
        billId: Long
    ): OperationResult

    /**
     * Отменить готовку уже распечатанного товара
     * @param billItemId Id товара
     * @return OperationResult
     */
    suspend fun emergencyCancel(
        billItemId: Long
    ): OperationResult

    /**
     * Печать счета
     * @param billId Id счета
     * @return OperationResult
     */
    suspend fun printBill(billId: Long): OperationResult

    /**
     * Закрытие счета
     * @param billId Id счета
     * @return OperationResult
     */
    suspend fun closeBill(billId: Long): OperationResult
}