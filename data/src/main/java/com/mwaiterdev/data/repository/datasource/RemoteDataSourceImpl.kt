package com.mwaiterdev.data.repository.datasource

import com.mwaiterdev.data.api.WaiterApi
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

    override suspend fun createBill(tableId: Long, userId: Long): NewBillResponse =
        waiterApi.createBillAsync(tableId = tableId, userId = userId).await()

    override suspend fun getBillInfo(billId: Long): BillsInfoResponse =
        waiterApi.getBillInfoAsync(billId = billId).await()

    override suspend fun addItemIntoBill(
        billId: Long,
        itemId: Long,
        amount: Float,
        price: Float
    ): OperationResult =
        waiterApi.addItemIntoBillAsync(
            billId = billId,
            itemId = itemId,
            amount = amount,
            price = price
        ).await()

    override suspend fun updateAmount(
        billItemId: Long,
        amount: Float,
        price: Float
    ): OperationResult =
        waiterApi.updateAmountAsync(
            billItemId = billItemId,
            amount = amount,
            price = price
        ).await()

    override suspend fun deleteItem(billItemId: Long): OperationResult =
        waiterApi.deleteItemAsync(billItemId).await()

    override suspend fun search(text: String): ItemsResponse =
        waiterApi.searchAsync(text).await()

    override suspend fun updateFavouriteState(favourite: Int, itemId: Long): OperationResult =
        waiterApi.updateFavouriteStateAsync(favourite, itemId).await()

    override suspend fun deleteBill(billId: Long): OperationResult =
        waiterApi.deleteBillAsync(billId).await()

    override suspend fun cookBill(billId: Long): OperationResult =
        waiterApi.cookBillAsync(billId).await()

    override suspend fun emergencyCancel(billItemId: Long): OperationResult =
        waiterApi.emergencyCancelAsync(billItemId).await()

    override suspend fun printBill(billId: Long): OperationResult =
        waiterApi.printBillAsync(billId = billId).await()

    override suspend fun closeBill(billId: Long): OperationResult =
        waiterApi.closeBillAsync(billId = billId).await()
}