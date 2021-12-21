package com.mwaiterdev.domain.models

data class TableItem(
    val tableId: Int,
    val tableGroupId: Int,
    val name: String,
    val state: String,
    val askCustomers: Boolean,
    val maxCustomers: Int
) : ITableItem