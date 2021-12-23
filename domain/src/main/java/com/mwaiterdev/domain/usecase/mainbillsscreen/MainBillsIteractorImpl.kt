package com.mwaiterdev.domain.usecase.mainbillsscreen

import com.mwaiterdev.domain.models.TableGroup
import com.mwaiterdev.domain.repository.Repository

class MainBillsIteractorImpl(
    private val repository: Repository
) : MainBillsIteractor{
    override suspend fun getFilterDataTableGroups(): List<TableGroup> = repository.getHalls()
}