package com.mwaiterdev.waiter.ui.bills.adapters.interfaces

interface BillsFilter {
    fun getMineBills(userId: Long, isCheck: Boolean)
}