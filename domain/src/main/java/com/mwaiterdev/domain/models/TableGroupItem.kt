package com.mwaiterdev.domain.models

data class TableGroupItem(
    val tableGroupId: Long,
    val name: String,
    val askCustomers: Boolean,
    var isExpanded: Boolean = false
) : ITableItem