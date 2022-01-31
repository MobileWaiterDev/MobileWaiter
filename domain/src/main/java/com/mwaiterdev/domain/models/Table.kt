package com.mwaiterdev.domain.models

data class Table(
    val tableId: Int,
    val tableGroupId: Int,
    val name: String,
    val state: String,
    val askCustomers: Boolean,
    val maxCustomers: Int,
    val userObserverName: String,
    val bill: Bill?
)
