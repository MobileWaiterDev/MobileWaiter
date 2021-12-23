package com.mwaiterdev.domain.usecase.billscreen

import com.mwaiterdev.domain.models.BillItem
import com.mwaiterdev.domain.models.Item
import com.mwaiterdev.domain.models.ItemGroup
import com.mwaiterdev.domain.repository.Repository

class BillInteractorImpl(
    private val repository: Repository
) : IBillInteractor {

    override suspend fun getBillItems(billId: Long): ArrayList<BillItem> =
        repository.getBillItems(billId = billId)

    override suspend fun getItemGroups(): ArrayList<ItemGroup> =
        repository.getItemGroups()

    override suspend fun getItems(itemGroupId: Long): List<Item> =
        repository.getItems(itemGroupId = itemGroupId)
}