package com.mwaiterdev.domain.repository

import com.mwaiterdev.domain.models.*

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
                        Item(1, 1, "Beef Stake", "Kotleta", true, Price(1, 12)),
                        Item(1, 1, "Beef Stake", "Kotleta", true, Price(1, 12)),
                        Item(1, 1, "Beef Stake", "Kotleta", true, Price(1, 12))
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
                        Item(1, 1, "Beef Stake", "Kotleta", true, Price(1, 12)),
                        Item(1, 1, "Beef Stake", "Kotleta", true, Price(1, 12)),
                        Item(1, 1, "Beef Stake", "Kotleta", true, Price(1, 12))
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
                        Item(1, 1, "Beef Stake", "Kotleta", true, Price(1, 12)),
                        Item(1, 1, "Beef Stake", "Kotleta", true, Price(1, 12)),
                        Item(1, 1, "Beef Stake", "Kotleta", true, Price(1, 12))
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
                        Item(1, 1, "Beef Stake", "Kotleta", true, Price(1, 12)),
                        Item(1, 1, "Beef Stake", "Kotleta", true, Price(1, 12)),
                        Item(1, 1, "Beef Stake", "Kotleta", true, Price(1, 12))
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
                        Item(1, 1, "Beef Stake", "Kotleta", true, Price(1, 12)),
                        Item(1, 1, "Beef Stake", "Kotleta", true, Price(1, 12)),
                        Item(1, 1, "Beef Stake", "Kotleta", true, Price(1, 12))
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
                        Item(1, 1, "Beef Stake", "Kotleta", true, Price(1, 12)),
                        Item(1, 1, "Beef Stake", "Kotleta", true, Price(1, 12)),
                        Item(1, 1, "Beef Stake", "Kotleta", true, Price(1, 12))
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
                        Item(1, 1, "Beef Stake", "Kotleta", true, Price(1, 12)),
                        Item(1, 1, "Beef Stake", "Kotleta", true, Price(1, 12)),
                        Item(1, 1, "Beef Stake", "Kotleta", true, Price(1, 12))
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
                        Item(1, 1, "Beef Stake", "Kotleta", true, Price(1, 12)),
                        Item(1, 1, "Beef Stake", "Kotleta", true, Price(1, 12)),
                        Item(1, 1, "Beef Stake", "Kotleta", true, Price(1, 12))
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
                        Item(1, 1, "Beef Stake", "Kotleta", true, Price(1, 12)),
                        Item(1, 1, "Beef Stake", "Kotleta", true, Price(1, 12)),
                        Item(1, 1, "Beef Stake", "Kotleta", true, Price(1, 12))
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
                        Item(1, 1, "Beef Stake", "Kotleta", true, Price(1, 12)),
                        Item(1, 1, "Beef Stake", "Kotleta", true, Price(1, 12)),
                        Item(1, 1, "Beef Stake", "Kotleta", true, Price(1, 12))
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
                        Item(1, 1, "Beef Stake", "Kotleta", true, Price(1, 12)),
                        Item(1, 1, "Beef Stake", "Kotleta", true, Price(1, 12)),
                        Item(1, 1, "Beef Stake", "Kotleta", true, Price(1, 12))
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
                        Item(1, 1, "Beef Stake", "Kotleta", true, Price(1, 12)),
                        Item(1, 1, "Beef Stake", "Kotleta", true, Price(1, 12)),
                        Item(1, 1, "Beef Stake", "Kotleta", true, Price(1, 12))
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
                        Item(1, 1, "Beef Stake", "Kotleta", true, Price(1, 12)),
                        Item(1, 1, "Beef Stake", "Kotleta", true, Price(1, 12)),
                        Item(1, 1, "Beef Stake", "Kotleta", true, Price(1, 12))
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
                        Item(1, 1, "Beef Stake", "Kotleta", true, Price(1, 12)),
                        Item(1, 1, "Beef Stake", "Kotleta", true, Price(1, 12)),
                        Item(1, 1, "Beef Stake", "Kotleta", true, Price(1, 12))
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
                        Item(1, 1, "Beef Stake", "Kotleta", true, Price(1, 12)),
                        Item(1, 1, "Beef Stake", "Kotleta", true, Price(1, 12)),
                        Item(1, 1, "Beef Stake", "Kotleta", true, Price(1, 12))
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
                        Item(1, 1, "Beef Stake", "Kotleta", true, Price(1, 12)),
                        Item(1, 1, "Beef Stake", "Kotleta", true, Price(1, 12)),
                        Item(1, 1, "Beef Stake", "Kotleta", true, Price(1, 12))
                    )
                )
            )
        )
    )
)

fun mockDataTableGroups(): List<String> =
    listOf("Все залы", "Зал 1", "Зал 2", "Зал 3", "Зал 4")