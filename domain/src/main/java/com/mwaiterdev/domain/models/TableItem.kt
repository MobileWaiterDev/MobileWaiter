package com.mwaiterdev.domain.models

data class TableItem(
    val tableId: Long,
    val tableGroupId: Long,
    val name: String,
    val state: Int,
    val askCustomers: Boolean,
    val maxCustomers: Int
) : ITableItem