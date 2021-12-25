package com.mwaiterdev.domain.repository

import com.mwaiterdev.domain.models.TableGroup
import com.mwaiterdev.domain.models.User
import com.mwaiterdev.domain.models.response.*
import com.mwaiterdev.domain.repository.datasource.IRemoteDataSource

class RepositoryImpl(private val dataSource: IRemoteDataSource) : Repository {
    override suspend fun getWaitress(id: Int): User {
        TODO("Not yet implemented")
    }

    override suspend fun getHalls(): List<TableGroup> {
        TODO("Not yet implemented")
    }

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
}