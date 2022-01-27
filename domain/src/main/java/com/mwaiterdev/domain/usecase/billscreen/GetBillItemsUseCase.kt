package com.mwaiterdev.domain.usecase.billscreen

import com.mwaiterdev.domain.models.BillItem
import com.mwaiterdev.domain.models.response.BillItems
import com.mwaiterdev.domain.repository.Repository

class GetBillItemsUseCase(private val repository: Repository) {

    /**
     * Получить список товаров в счете
     * @param billId Id счета
     * @param needScrollToEndPosition Нужно ли скроллить до конца списка
     * @return BillItems
     */
    suspend fun execute(billId: Long, needScrollToEndPosition: Boolean): BillItems {
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
                        name = billItem.name,
                        printed = billItem.printed
                    )
                )
            }
            return BillItems(items, needScrollToEndPosition)
        } else {
            return BillItems(arrayListOf())
        }
    }

    companion object {
        private const val ZERO_VALUE = 0L
    }
}