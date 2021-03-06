package com.mwaiterdev.data.api

import com.mwaiterdev.domain.models.response.*
import kotlinx.coroutines.Deferred
import retrofit2.http.*

interface WaiterApi {

    /**
     * Получить пользователя по пин-коду
     * @param pin Пин-код
     * @return UserResponse
     */
    @GET("/MWaiter/?Method=getUserByPin")
    fun getUserByPinAsync(@Query("pin") pin: String): Deferred<UserResponse>

    /**
     * Получить все счета
     * @return BillsResponse
     */
    @GET("/MWaiter/?Method=getBills")
    fun getBillsAsync(): Deferred<BillsResponse>

    /**
     * Получить все столы
     * @return TablesResponse
     */
    @GET("/MWaiter/?Method=getTables")
    fun getTablesAsync(): Deferred<TablesResponse>

    /**
     * Получить категории меню
     * @return ItemGroupsResponse
     */
    @GET("/MWaiter/?Method=getItemCategory")
    fun getItemGroupsAsync(): Deferred<ItemGroupsResponse>

    /**
     * Получить список товаров меню по itemGroupId
     * @param itemGroupId Id группы товаров
     * @return ItemsResponse
     */
    @GET("/MWaiter/?Method=getItemsByGroupItemId")
    fun getItemsByIdAsync(@Query("itemGroupId") itemGroupId: Long): Deferred<ItemsResponse>

    /**
     * Получить список товаров счета
     * @param billId Id счета
     * @return BillItemsResponse
     */
    @GET("/MWaiter/?Method=getBillItemsByBillId")
    fun getBillItemsByIdAsync(@Query("billId") billId: Long): Deferred<BillItemsResponse>

    /**
     * Получить список залов
     * @return TableGroupsResponse
     */
    @GET("/MWaiter/?Method=getTableGroups")
    fun getTableGroupAsync(): Deferred<TableGroupsResponse>

    /**
     * Создать новый счет
     * @param tableId Id столика
     * @param userId Id пользователя
     * @return NewBillResponse
     */
    @GET("/MWaiter/?Method=createBill")
    fun createBillAsync(
        @Query("tableId") tableId: Long,
        @Query("userId") userId: Long
    ): Deferred<NewBillResponse>

    /**
     * Информация о счете
     * @param billId Id счета
     * @return BillInfoResponse
     */
    @GET("/MWaiter/?Method=getBillInfo")
    fun getBillInfoAsync(@Query("billId") billId: Long): Deferred<BillsInfoResponse>

    /**
     * Добавить товар в счет
     * @param billId Id счета
     * @param itemId Id товара
     * @param amount Количество
     * @param price Цена
     * @return OperationResult
     */
    @FormUrlEncoded
    @POST("/MWaiter/?Method=addItemIntoBill")
    fun addItemIntoBillAsync(
        @Field("billId") billId: Long,
        @Field("itemId") itemId: Long,
        @Field("amount") amount: Float,
        @Field("price") price: Float
    ): Deferred<OperationResult>

    /**
     * Изменить количество товара в счете
     * @param billItemId Id товара в счете
     * @param amount Количество
     * @param price Цена
     * @return OperationResult
     */
    @FormUrlEncoded
    @POST("/MWaiter/?Method=updateAmount")
    fun updateAmountAsync(
        @Field("billItemId") billItemId: Long,
        @Field("amount") amount: Float,
        @Field("price") price: Float
    ): Deferred<OperationResult>

    /**
     * Удалить товар из счета
     * @param billItemId Id товара в счете
     * @return OperationResult
     */
    @FormUrlEncoded
    @POST("/MWaiter/?Method=deleteItem")
    fun deleteItemAsync(
        @Field("billItemId") billItemId: Long,
    ): Deferred<OperationResult>

    /**
     * Поиск товара по имени
     * @param text Текст для поиска
     * @return ItemsResponse
     */
    @FormUrlEncoded
    @POST("/MWaiter/?Method=findItemsByName")
    fun searchAsync(
        @Field("request") text: String
    ): Deferred<ItemsResponse>

    /**
     * Изменить статус избранного у товара
     * @param favourite Значение избраного, 0/1
     * @param itemId Id товара
     * @return OperationResult
     */
    @FormUrlEncoded
    @POST("/MWaiter/?Method=updateFavouriteStateItem")
    fun updateFavouriteStateAsync(
        @Field("favourite") favourite: Int,
        @Field("itemId") itemId: Long,
    ): Deferred<OperationResult>

    /**
     * Удалить счет
     * @param billId Id счета
     * @return OperationResult
     */
    @FormUrlEncoded
    @POST("/MWaiter/?Method=deleteBill")
    fun deleteBillAsync(
        @Field("billId") billId: Long,
    ): Deferred<OperationResult>

    /**
     * Готовить продукты
     * @param billId Id счета
     * @return OperationResult
     */
    @FormUrlEncoded
    @POST("/MWaiter/?Method=cookBill")
    fun cookBillAsync(
        @Field("billId") billId: Long,
    ): Deferred<OperationResult>

    /**
     * Отменить готовку уже распечатанного товара
     * @param billItemId Id товара
     * @return OperationResult
     */
    @FormUrlEncoded
    @POST("/MWaiter/?Method=emergencyDeleteItem")
    fun emergencyCancelAsync(
        @Field("billItemId") billItemId: Long,
    ): Deferred<OperationResult>

    /**
     * Печать счета
     * @param billId Id счета
     * @return OperationResult
     */
    @FormUrlEncoded
    @POST("/MWaiter/?Method=printBill")
    fun printBillAsync(
        @Field("billId") billId: Long
    ): Deferred<OperationResult>

    /**
     * Закрытие счета
     * @param billId Id счета
     * @return OperationResult
     */
    @FormUrlEncoded
    @POST("/MWaiter/?Method=closeBill")
    fun closeBillAsync(
        @Field("billId") billId: Long,
    ): Deferred<OperationResult>
}