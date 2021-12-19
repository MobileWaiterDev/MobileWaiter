package com.mwaiterdev.domain.models

data class Bill(
    val billId: Int,
    val tableId: Int,
    val createdByUserId: Int,
    val createTime: Int,
    val total: Float,
    val subTotal: Float,
    val customers: String,
    val billNumber: Int,
    val name: String,
    val printed: Boolean,
    val orders: List<Item>)
