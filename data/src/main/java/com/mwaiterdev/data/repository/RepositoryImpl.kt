package com.mwaiterdev.data.repository

import com.mwaiterdev.data.repository.datasource.IRemoteDataSource
import com.mwaiterdev.domain.models.TableGroup
import com.mwaiterdev.domain.models.User
import com.mwaiterdev.domain.models.response.*
import com.mwaiterdev.domain.repository.Repository

class RepositoryImpl(private val dataSource: IRemoteDataSource) : Repository {
    override suspend fun getWaitress(id: Int): User {
        TODO("Not yet implemented")
    }

    override suspend fun getHalls(): BillsResponse =
        dataSource.getBills()

    override suspend fun getTableGroups(): TableGroupsResponse =
        dataSource.getTableGroups()

    override suspend fun getTableGroupsWithTables(): List<TableGroup> {
        TODO("Not yet implemented")
    }

    override suspend fun getBillItemsById(billId: Long): BillItemsResponse =
        dataSource.getBillItemsById(billId = billId)

    override suspend fun getItemGroups(): ItemGroupsResponse =
        dataSource.getItemGroups()

    override suspend fun getItemsById(itemGroupId: Long): ItemsResponse =
        dataSource.getItemsById(itemGroupId = itemGroupId)

    override suspend fun getUserByPin(pin: String): UserResponse =
        dataSource.getUserByPin(pin = pin)

    override suspend fun getBills(): BillsResponse =
        dataSource.getBills()

    override suspend fun getTables(): TablesResponse =
        dataSource.getTables()

    override suspend fun createBill(tableId: Long): NewBillResponse =
        dataSource.createBill(tableId = tableId)

    override suspend fun getBillInfo(billId: Long): BillsInfoResponse =
        dataSource.getBillInfo(billId = billId)

    override suspend fun addItemIntoBill(
        billId: Long,
        itemId: Long,
        amount: Float,
        price: Float
    ): OperationResult =
        dataSource.addItemIntoBill(
            billId = billId,
            itemId = itemId,
            amount = amount,
            price = price
        )

    override suspend fun updateAmount(
        billItemId: Long,
        amount: Float,
        price: Float
    ): OperationResult =
        dataSource.updateAmount(
            billItemId = billItemId,
            amount = amount,
            price = price
        )

    override suspend fun deleteItem(billItemId: Long): OperationResult =
        dataSource.deleteItem(billItemId)

    override suspend fun search(text: String): ItemsResponse =
        dataSource.search(text)

    override suspend fun updateFavouriteState(favourite: Int, itemId: Long): OperationResult =
        dataSource.updateFavouriteState(favourite, itemId)

    override suspend fun deleteBill(billId: Long): OperationResult =
        dataSource.deleteBill(billId)
}