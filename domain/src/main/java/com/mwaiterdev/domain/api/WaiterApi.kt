package com.mwaiterdev.domain.api

import com.mwaiterdev.domain.models.response.*
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

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
}