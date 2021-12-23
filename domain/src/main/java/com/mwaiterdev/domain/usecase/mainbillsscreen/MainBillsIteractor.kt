package com.mwaiterdev.domain.usecase.mainbillsscreen

import com.mwaiterdev.domain.models.TableGroup

interface MainBillsIteractor {
    suspend fun getFilterDataTableGroups(): List<TableGroup>
}