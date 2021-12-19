package com.mwaiterdev.domain.models

data class TableGroup(
    val tableGroupId: Int,
    val name: String,
    val askCustomers: Boolean,
    val tables: List<Table>
)
