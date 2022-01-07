package com.mwaiterdev.domain.usecase.billscreen

import com.mwaiterdev.domain.models.*
import com.mwaiterdev.domain.models.response.BillItems
import com.mwaiterdev.domain.models.response.ItemGroups
import com.mwaiterdev.domain.repository.Repository

class BillInteractorImpl(
    private val repository: Repository
) : IBillInteractor {

    override suspend fun getBillItemsById(billId: Long): BillItems {
        if (billId == ZERO_VALUE) {
            return BillItems(arrayListOf())
        }
        val result = repository.getBillItemsById(billId = billId)
        if (result.success) {
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
            return BillItems(items)
        } else {
            return BillItems(arrayListOf())
        }
    }

    override suspend fun getMenu(itemGroupId: Long): ItemGroups {
        if (itemGroupId > ZERO_VALUE) {
            val result = repository.getItemsById(itemGroupId = itemGroupId)
            if (result.success) {
                val items: ArrayList<IMenuItem> = arrayListOf()
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
                return ItemGroups(items)
            } else {
                return ItemGroups(arrayListOf())
            }
        } else {
            val result = repository.getItemGroups()
            if (result.success) {
                val items: ArrayList<IMenuItem> = arrayListOf()
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
                return ItemGroups(items)
            } else {
                return ItemGroups(arrayListOf())
            }
        }
    }

    companion object {
        const val ZERO_VALUE = 0L
    }
}