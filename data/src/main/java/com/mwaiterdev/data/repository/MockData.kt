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

fun mockTableGroupsWithTables(): List<TableGroup> = listOf(
    TableGroup(
        1,
        "Зал 1",
        true,
        tables = listOf(
            Table(
                1,
                1,
                "1",
                "Free",
                true,
                2,
                bill = null,
                userObserverName = ""
            ),
            Table(
                2,
                1,
                "2",
                "Free",
                true,
                2,
                bill = null,
                userObserverName = ""
            ),
            Table(
                3,
                1,
                "3",
                "Free",
                true,
                2,
                bill = null,
                userObserverName = ""
            ),
            Table(
                4,
                1,
                "4",
                "Free",
                true,
                2,
                bill = null,
                userObserverName = ""
            ),
            Table(
                5,
                1,
                "5",
                "Free",
                true,
                2,
                bill = null,
                userObserverName = ""
            ),
            Table(
                6,
                1,
                "6",
                "Free",
                true,
                2,
                bill = null,
                userObserverName = ""
            ),
            Table(
                7,
                1,
                "7",
                "Free",
                true,
                2,
                bill = null,
                userObserverName = ""
            ),
            Table(
                8,
                1,
                "8",
                "Free",
                true,
                2,
                bill = null,
                userObserverName = ""
            ),
            Table(
                9,
                1,
                "9",
                "Free",
                true,
                2,
                bill = null,
                userObserverName = ""
            )
        )
    ),
    TableGroup(
        2,
        "Зал 2",
        true,
        tables = listOf(
            Table(
                4,
                2,
                "1",
                "Free",
                true,
                2,
                bill = null,
                userObserverName = ""
            ),
            Table(
                5,
                2,
                "2",
                "Free",
                true,
                2,
                bill = null,
                userObserverName = ""
            ),
            Table(
                6,
                2,
                "3",
                "Free",
                true,
                2,
                bill = null,
                userObserverName = ""
            ),
            Table(
                5,
                2,
                "4",
                "Free",
                true,
                2,
                bill = null,
                userObserverName = ""
            ),
            Table(
                6,
                2,
                "5",
                "Free",
                true,
                2,
                bill = null,
                userObserverName = ""
            )
        )
    ),
    TableGroup(
        3,
        "Зал 3",
        true,
        tables = listOf(
            Table(
                7,
                3,
                "1",
                "Free",
                true,
                2,
                bill = null,
                userObserverName = ""
            ),
            Table(
                8,
                3,
                "2",
                "Free",
                true,
                2,
                bill = null,
                userObserverName = ""
            ),
            Table(
                9,
                3,
                "3",
                "Free",
                true,
                2,
                bill = null,
                userObserverName = ""
            )
        )
    ),
    TableGroup(
        4,
        "Зал 4",
        true,
        tables = listOf(
            Table(
                10,
                4,
                "1",
                "Free",
                true,
                2,
                bill = null,
                userObserverName = ""
            ),
            Table(
                11,
                4,
                "2",
                "Free",
                true,
                2,
                bill = null,
                userObserverName = ""
            ),
            Table(
                12,
                4,
                "3",
                "Free",
                true,
                2,
                bill = null,
                userObserverName = ""
            ), Table(
                10,
                4,
                "4",
                "Free",
                true,
                2,
                bill = null,
                userObserverName = ""
            ),
            Table(
                11,
                4,
                "5",
                "Free",
                true,
                2,
                bill = null,
                userObserverName = ""
            ),
            Table(
                12,
                4,
                "6",
                "Free",
                true,
                2,
                bill = null,
                userObserverName = ""
            )
        )
    )
)

fun mockBillItems(): BillItemsResponse =
    BillItemsResponse(arrayListOf(), message = "OK", success = true)

fun mockItemGroups(): ItemGroupsResponse =
    ItemGroupsResponse(arrayListOf(), message = "OK", success = true)

fun mockItems(itemGroupId: Long): ItemsResponse =
    ItemsResponse(arrayListOf(), message = "OK", success = true)