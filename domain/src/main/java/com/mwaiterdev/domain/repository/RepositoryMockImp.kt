package com.mwaiterdev.domain.repository

import com.mwaiterdev.domain.models.TableGroup
import com.mwaiterdev.domain.models.User
import com.mwaiterdev.domain.models.response.*

class RepositoryMockImp : Repository {
    override suspend fun getWaitress(id: Int): User = mockDataWaitress()
    override suspend fun getHalls(): BillsResponse = mockDataHalls()

    override suspend fun getTableGroups(): TableGroupsResponse = mockDataTableGroups()
    override suspend fun getTableGroupsWithTables(): List<TableGroup> =
        mockTableGroupsWithTables()

    override suspend fun getBillItemsById(billId: Long): BillItemsResponse =
        mockBillItems()

    override suspend fun getItemGroups(): ItemGroupsResponse =
        mockItemGroups()

    override suspend fun getItemsById(itemGroupId: Long): ItemsResponse =
        mockItems(itemGroupId = itemGroupId)

    override suspend fun getUserByPin(pin: String): UserResponse {
        TODO("Not yet implemented")
    }

    override suspend fun getBills(): BillsResponse {
        TODO("Not yet implemented")
    }

    override suspend fun getTables(): TablesResponse {
        TODO("Not yet implemented")
    }

    override suspend fun createBill(tableId: Long): NewBillResponse {
        TODO("Not yet implemented")
    }

    override suspend fun getBillInfo(billId: Long): BillsInfoResponse {
        TODO("Not yet implemented")
    }

    override suspend fun addItemIntoBill(
        billId: Long,
        itemId: Long,
        amount: Float,
        price: Float
    ): OperationResult {
        TODO("Not yet implemented")
    }

    override suspend fun updateAmount(
        billItemId: Long,
        amount: Float,
        price: Float
    ): OperationResult {
        TODO("Not yet implemented")
    }

    override suspend fun deleteItem(billItemId: Long): OperationResult {
        TODO("Not yet implemented")
    }
}