package com.mwaiterdev.waiter.ui.bills.adapters.interfaces

import com.mwaiterdev.domain.models.TableGroup

interface BillsFilter {
    fun getMineBills(data: List<TableGroup>?)
    fun filter(data: List<TableGroup>?)
}