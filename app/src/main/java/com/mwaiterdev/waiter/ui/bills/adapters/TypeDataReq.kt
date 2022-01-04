package com.mwaiterdev.waiter.ui.bills.adapters

sealed class TypeDataReq {
    object CreatedTime : TypeDataReq()
    object UserName : TypeDataReq()
    object BillsCount : TypeDataReq()
    object TotalBill : TypeDataReq()
}