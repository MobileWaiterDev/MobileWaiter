package com.mwaiterdev.domain.repository

import com.mwaiterdev.domain.models.*
import com.mwaiterdev.domain.models.response.BillItemsResponse
import com.mwaiterdev.domain.models.response.ItemGroupsResponse
import com.mwaiterdev.domain.models.response.ItemsResponse
import com.mwaiterdev.domain.models.response.TableGroupsResponse

fun mockDataWaitress(): User = User(
    1, 1, "Василий", true, 1234
)

fun mockDataHalls(): List<TableGroup> = listOf(
    TableGroup(
        1,
        "Зал 1",
        true,
        listOf(
            Table(
                1,
                1,
                "Table 1",
                "Busy",
                true,
                2,
                "Василий",
                Bill(
                    1,
                    1,
                    1,
                    1200,
                    1555.00f,
                    1555.00f,
                    "Vasya",
                    1212,
                    "Name",
                    true,
                    listOf(
                        Item(1, 1, "Beef Stake", "Kotleta", true, Price(1, 12.00f)),
                        Item(1, 1, "Beef Stake", "Kotleta", true, Price(1, 12.00f)),
                        Item(1, 1, "Beef Stake", "Kotleta", true, Price(1, 12.00f))
                    )
                )
            ),
            Table(
                1,
                1,
                "Table 1",
                "Busy",
                true,
                2,
                "Василий",
                Bill(
                    1,
                    1,
                    1,
                    1200,
                    1555.00f,
                    1555.00f,
                    "Vasya",
                    1212,
                    "Name",
                    true,
                    listOf(
                        Item(1, 1, "Beef Stake", "Kotleta", true, Price(1, 12.00f)),
                        Item(1, 1, "Beef Stake", "Kotleta", true, Price(1, 12.00f)),
                        Item(1, 1, "Beef Stake", "Kotleta", true, Price(1, 12.00f))
                    )
                )
            ),
            Table(
                1,
                1,
                "Table 1",
                "Busy",
                true,
                2,
                "Василий",
                Bill(
                    1,
                    1,
                    1,
                    1200,
                    1555.00f,
                    1555.00f,
                    "Vasya",
                    1212,
                    "Name",
                    true,
                    listOf(
                        Item(1, 1, "Beef Stake", "Kotleta", true, Price(1, 12.00f)),
                        Item(1, 1, "Beef Stake", "Kotleta", true, Price(1, 12.00f)),
                        Item(1, 1, "Beef Stake", "Kotleta", true, Price(1, 12.00f))
                    )
                )
            ),
            Table(
                1,
                1,
                "Table 1",
                "Busy",
                true,
                2,
                "Василий",
                Bill(
                    1,
                    1,
                    1,
                    1200,
                    1555.00f,
                    1555.00f,
                    "Vasya",
                    1212,
                    "Name",
                    true,
                    listOf(
                        Item(1, 1, "Beef Stake", "Kotleta", true, Price(1, 12.00f)),
                        Item(1, 1, "Beef Stake", "Kotleta", true, Price(1, 12.00f)),
                        Item(1, 1, "Beef Stake", "Kotleta", true, Price(1, 12.00f))
                    )
                )
            )
        )
    ),
    TableGroup(
        1, "Зал 2", true,
        listOf(
            Table(
                1,
                1,
                "Table 1",
                "Busy",
                true,
                2,
                "Василий",
                Bill(
                    1,
                    1,
                    1,
                    1200,
                    1555.00f,
                    1555.00f,
                    "Vasya",
                    1212,
                    "Name",
                    true,
                    listOf(
                        Item(1, 1, "Beef Stake", "Kotleta", true, Price(1, 12.00f)),
                        Item(1, 1, "Beef Stake", "Kotleta", true, Price(1, 12.00f)),
                        Item(1, 1, "Beef Stake", "Kotleta", true, Price(1, 12.00f))
                    )
                )
            ),
            Table(
                1,
                1,
                "Table 1",
                "Busy",
                true,
                2,
                "Василий",
                Bill(
                    1,
                    1,
                    1,
                    1200,
                    1555.00f,
                    1555.00f,
                    "Vasya",
                    1212,
                    "Name",
                    true,
                    listOf(
                        Item(1, 1, "Beef Stake", "Kotleta", true, Price(1, 12.00f)),
                        Item(1, 1, "Beef Stake", "Kotleta", true, Price(1, 12.00f)),
                        Item(1, 1, "Beef Stake", "Kotleta", true, Price(1, 12.00f))
                    )
                )
            ),
            Table(
                1,
                1,
                "Table 1",
                "Busy",
                true,
                2,
                "Василий",
                Bill(
                    1,
                    1,
                    1,
                    1200,
                    1555.00f,
                    1555.00f,
                    "Vasya",
                    1212,
                    "Name",
                    true,
                    listOf(
                        Item(1, 1, "Beef Stake", "Kotleta", true, Price(1, 12.00f)),
                        Item(1, 1, "Beef Stake", "Kotleta", true, Price(1, 12.00f)),
                        Item(1, 1, "Beef Stake", "Kotleta", true, Price(1, 12.00f))
                    )
                )
            ),
            Table(
                1,
                1,
                "Table 1",
                "Busy",
                true,
                2,
                "Василий",
                Bill(
                    1,
                    1,
                    1,
                    1200,
                    1555.00f,
                    1555.00f,
                    "Vasya",
                    1212,
                    "Name",
                    true,
                    listOf(
                        Item(1, 1, "Beef Stake", "Kotleta", true, Price(1, 12.00f)),
                        Item(1, 1, "Beef Stake", "Kotleta", true, Price(1, 12.00f)),
                        Item(1, 1, "Beef Stake", "Kotleta", true, Price(1, 12.00f))
                    )
                )
            )
        )
    ),
    TableGroup(
        1, "Зал 3", true,
        listOf(
            Table(
                1,
                1,
                "Table 1",
                "Busy",
                true,
                2,
                "Василий",
                Bill(
                    1,
                    1,
                    1,
                    1200,
                    1555.00f,
                    1555.00f,
                    "Vasya",
                    1212,
                    "Name",
                    true,
                    listOf(
                        Item(1, 1, "Beef Stake", "Kotleta", true, Price(1, 12.00f)),
                        Item(1, 1, "Beef Stake", "Kotleta", true, Price(1, 12.00f)),
                        Item(1, 1, "Beef Stake", "Kotleta", true, Price(1, 12.00f))
                    )
                )
            ),
            Table(
                1,
                1,
                "Table 1",
                "Busy",
                true,
                2,
                "Василий",
                Bill(
                    1,
                    1,
                    1,
                    1200,
                    1555.00f,
                    1555.00f,
                    "Vasya",
                    1212,
                    "Name",
                    true,
                    listOf(
                        Item(1, 1, "Beef Stake", "Kotleta", true, Price(1, 12.00f)),
                        Item(1, 1, "Beef Stake", "Kotleta", true, Price(1, 12.00f)),
                        Item(1, 1, "Beef Stake", "Kotleta", true, Price(1, 12.00f))
                    )
                )
            ),
            Table(
                1,
                1,
                "Table 1",
                "Busy",
                true,
                2,
                "Василий",
                Bill(
                    1,
                    1,
                    1,
                    1200,
                    1555.00f,
                    1555.00f,
                    "Vasya",
                    1212,
                    "Name",
                    true,
                    listOf(
                        Item(1, 1, "Beef Stake", "Kotleta", true, Price(1, 12.00f)),
                        Item(1, 1, "Beef Stake", "Kotleta", true, Price(1, 12.00f)),
                        Item(1, 1, "Beef Stake", "Kotleta", true, Price(1, 12.00f))
                    )
                )
            ),
            Table(
                1,
                1,
                "Table 1",
                "Busy",
                true,
                2,
                "Василий",
                Bill(
                    1,
                    1,
                    1,
                    1200,
                    1555.00f,
                    1555.00f,
                    "Vasya",
                    1212,
                    "Name",
                    true,
                    listOf(
                        Item(1, 1, "Beef Stake", "Kotleta", true, Price(1, 12.00f)),
                        Item(1, 1, "Beef Stake", "Kotleta", true, Price(1, 12.00f)),
                        Item(1, 1, "Beef Stake", "Kotleta", true, Price(1, 12.00f))
                    )
                )
            )
        )
    ),
    TableGroup(
        1, "Зал 4", true,
        listOf(
            Table(
                1,
                1,
                "Table 1",
                "Busy",
                true,
                2,
                "Василий",
                Bill(
                    1,
                    1,
                    1,
                    1200,
                    1555.00f,
                    1555.00f,
                    "Vasya",
                    1212,
                    "Name",
                    true,
                    listOf(
                        Item(1, 1, "Beef Stake", "Kotleta", true, Price(1, 12.00f)),
                        Item(1, 1, "Beef Stake", "Kotleta", true, Price(1, 12.00f)),
                        Item(1, 1, "Beef Stake", "Kotleta", true, Price(1, 12.00f))
                    )
                )
            ),
            Table(
                1,
                1,
                "Table 1",
                "Busy",
                true,
                2,
                "Василий",
                Bill(
                    1,
                    1,
                    1,
                    1200,
                    1555.00f,
                    1555.00f,
                    "Vasya",
                    1212,
                    "Name",
                    true,
                    listOf(
                        Item(1, 1, "Beef Stake", "Kotleta", true, Price(1, 12.00f)),
                        Item(1, 1, "Beef Stake", "Kotleta", true, Price(1, 12.00f)),
                        Item(1, 1, "Beef Stake", "Kotleta", true, Price(1, 12.00f))
                    )
                )
            ),
            Table(
                1,
                1,
                "Table 1",
                "Busy",
                true,
                2,
                "Василий",
                Bill(
                    1,
                    1,
                    1,
                    1200,
                    1555.00f,
                    1555.00f,
                    "Vasya",
                    1212,
                    "Name",
                    true,
                    listOf(
                        Item(1, 1, "Beef Stake", "Kotleta", true, Price(1, 12.00f)),
                        Item(1, 1, "Beef Stake", "Kotleta", true, Price(1, 12.00f)),
                        Item(1, 1, "Beef Stake", "Kotleta", true, Price(1, 12.00f))
                    )
                )
            ),
            Table(
                1,
                1,
                "Table 1",
                "Busy",
                true,
                2,
                "Василий",
                Bill(
                    1,
                    1,
                    1,
                    1200,
                    1555.00f,
                    1555.00f,
                    "Vasya",
                    1212,
                    "Name",
                    true,
                    listOf(
                        Item(1, 1, "Beef Stake", "Kotleta", true, Price(1, 12.00f)),
                        Item(1, 1, "Beef Stake", "Kotleta", true, Price(1, 12.00f)),
                        Item(1, 1, "Beef Stake", "Kotleta", true, Price(1, 12.00f))
                    )
                )
            )
        )
    )
)

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