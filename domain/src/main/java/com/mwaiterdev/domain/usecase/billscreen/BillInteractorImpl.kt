package com.mwaiterdev.domain.usecase.billscreen

import com.mwaiterdev.domain.models.BillItem
import com.mwaiterdev.domain.models.Item
import com.mwaiterdev.domain.models.ItemGroup
import com.mwaiterdev.domain.models.Price
import com.mwaiterdev.domain.repository.Repository

class BillInteractorImpl(
    private val repository: Repository
) : IBillInteractor {

    override suspend fun getBillItemsById(billId: Long): List<BillItem> {
        val result = repository.getBillItemsById(billId = billId)
        if (result.success && result.billItems.isNotEmpty()) {
            val items: ArrayList<BillItem> = arrayListOf()
            result.billItems?.forEach { billItem ->
                items.add(
                    BillItem(
                        billItemId = billItem.billItemId,
                        billId = billItem.billId,
                        itemId = billItem.itemId,
                        amount = billItem.amount,
                        price = billItem.price,
                        subTotal = billItem.subtotal,
                        total = billItem.total,
                        createTime = billItem.createTime,
                        name = billItem.name
                    )
                )
            }
            return items
        } else {
            return arrayListOf()
        }
    }

    override suspend fun getItemGroups(): List<ItemGroup> {
        val result = repository.getItemGroups()
        if (result.success && result.itemGroups.isNotEmpty()) {
            val items: ArrayList<ItemGroup> = arrayListOf()
            result.itemGroups?.forEach { itemGroup ->
                items.add(
                    ItemGroup(
                        itemGroupId = itemGroup.itemGroupId,
                        name = itemGroup.name,
                        shortName = itemGroup.shortName,
                        available = itemGroup.available
                    )
                )
            }
            return items
        } else {
            return arrayListOf()
        }
    }

    override suspend fun getItemsById(itemGroupId: Long): List<Item> {
        val result = repository.getItemsById(itemGroupId = itemGroupId)
        if (result.success && result.items.isNotEmpty()) {
            val items: ArrayList<Item> = arrayListOf()
            result.items?.forEach { item ->
                items.add(
                    Item(
                        itemId = item.itemId,
                        itemGroupId = item.itemGroupId,
                        name = item.name,
                        shorName = item.shortName,
                        available = item.available,
                        price = Price(itemId = item.itemId, price = item.price)
                    )
                )
            }
            return items
        } else {
            return arrayListOf()
        }
    }
}