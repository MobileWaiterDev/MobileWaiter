package com.mwaiterdev.domain.repository

import com.mwaiterdev.domain.models.TableGroup
import com.mwaiterdev.domain.models.User

class RepositoryImp : Repository {
    override suspend fun getWaitress(id: Int): User = mockDataWaitress()
    override suspend fun getHalls(): List<TableGroup> = mockDataHalls()
    override suspend fun getTableGroups(): List<String> = mockDataTableGroups()
}