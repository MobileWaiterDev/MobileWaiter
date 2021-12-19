package com.mwaiterdev.domain.repository

import com.mwaiterdev.domain.models.TableGroup
import com.mwaiterdev.domain.models.User

interface Repository {
    suspend fun getWaitress(id: Int): User
    suspend fun getHalls(): List<TableGroup>
}