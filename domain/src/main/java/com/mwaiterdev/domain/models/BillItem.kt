package com.mwaiterdev.domain.models

data class BillItem(
    val billItemId: Int,
    val billId: Int,
    val itemId: Int,
    val amount: Int,
    val price: Float,
    val subTotal: Float,
    val total: Float,
    val createTime: String
)