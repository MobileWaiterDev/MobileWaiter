package com.mwaiterdev.domain.models

data class BillItem(
    val billItemId: Long,
    val billId: Long,
    val itemId: Long,
    val amount: Float,
    val price: Float,
    val subTotal: Float,
    val total: Float,
    val createTime: String,
    val name: String,
    val printed: Int
)