package com.mwaiterdev.domain.repository

import com.mwaiterdev.domain.models.*

class RepositoryImp : Repository {
    override suspend fun getWaitress(id: Int): User = mockDataWaitress()
    override suspend fun getHalls(): List<TableGroup> = mockDataHalls()
    override suspend fun getTableGroups(): List<String> = mockDataTableGroups()
    override suspend fun getTableGroupsWithTables(): List<TableGroup> =
        mockTableGroupsWithTables()

    override suspend fun getBillItems(billId: Long): ArrayList<BillItem> =
        mockBillItems()

    override suspend fun getItemGroups(): ArrayList<ItemGroup> =
        mockItemGroups()

    override suspend fun getItems(itemGroupId: Long): List<Item> =
        mockItems(itemGroupId = itemGroupId)
}