package com.mwaiterdev.data.repository

import com.mwaiterdev.domain.models.Table
import com.mwaiterdev.domain.models.TableGroup
import com.mwaiterdev.domain.models.User
import com.mwaiterdev.domain.models.response.*

fun mockDataWaitress(): User = User(
    1, 1, "Василий", true, "1234", ""
)

fun mockDataHalls(): BillsResponse = BillsResponse("ok", true, null)

fun mockDataTableGroups(): TableGroupsResponse =
    TableGroupsResponse(arrayListOf(), message = "ОК", success = true)

fun mockTableGroupsWithTables(): List<TableGroup> = listOf()

fun mockBillItems(): BillItemsResponse =
    BillItemsResponse(arrayListOf(), message = "OK", success = true)

fun mockItemGroups(): ItemGroupsResponse =
    ItemGroupsResponse(arrayListOf(), message = "OK", success = true)

fun mockItems(itemGroupId: Long): ItemsResponse =
    ItemsResponse(arrayListOf(), message = "OK", success = true)