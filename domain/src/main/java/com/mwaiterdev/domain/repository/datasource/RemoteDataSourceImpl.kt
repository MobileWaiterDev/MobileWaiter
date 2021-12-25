package com.mwaiterdev.domain.repository.datasource

import com.mwaiterdev.domain.api.WaiterApi
import com.mwaiterdev.domain.models.response.*

class RemoteDataSourceImpl(private val waiterApi: WaiterApi) : IRemoteDataSource {
    override suspend fun getUserByPin(pin: String): UserResponse =
        waiterApi.getUserByPinAsync(pin).await()

    override suspend fun getBills(): BillsResponse =
        waiterApi.getBillsAsync().await()

    override suspend fun getTables(): TablesResponse =
        waiterApi.getTablesAsync().await()

    override suspend fun getItemGroups(): ItemGroupsResponse =
        waiterApi.getItemGroupsAsync().await()

    override suspend fun getItemsById(itemGroupId: Long): ItemsResponse =
        waiterApi.getItemsByIdAsync(itemGroupId = itemGroupId).await()

    override suspend fun getBillItemsById(billId: Long): BillItemsResponse =
        waiterApi.getBillItemsByIdAsync(billId = billId).await()

    override suspend fun getTableGroups(): TableGroupsResponse =
        waiterApi.getTableGroupAsync().await()
}