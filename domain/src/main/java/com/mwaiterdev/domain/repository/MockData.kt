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

fun mockBillItems(): ArrayList<BillItem> =
    arrayListOf(
        BillItem(
            billItemId = 1,
            billId = 1,
            itemId = 1,
            amount = 2,
            price = 5.0f,
            subTotal = 10.0f,
            total = 10.0f,
            createTime = "2021-12-12 09:35:00",
            name = "Пеперони"
        ),
        BillItem(
            billItemId = 2,
            billId = 1,
            itemId = 5,
            amount = 1,
            price = 8.0f,
            subTotal = 8.0f,
            total = 8.0f,
            createTime = "2021-12-12 09:35:15",
            name = "Золотой ключик"
        ),
        BillItem(
            billItemId = 3,
            billId = 1,
            itemId = 6,
            amount = 3,
            price = 12.5f,
            subTotal = 37.5f,
            total = 37.5f,
            createTime = "2021-12-12 09:35:50",
            name = "Рачки"
        ),
        BillItem(
            billItemId = 4,
            billId = 1,
            itemId = 4,
            amount = 2,
            price = 5.0f,
            subTotal = 10.0f,
            total = 10.0f,
            createTime = "2021-12-12 09:35:00",
            name = "Апельсины"
        ),
        BillItem(
            billItemId = 5,
            billId = 1,
            itemId = 3,
            amount = 1,
            price = 8.0f,
            subTotal = 8.0f,
            total = 8.0f,
            createTime = "2021-12-12 09:35:15",
            name = "Яблоки"
        ),
        BillItem(
            billItemId = 6,
            billId = 1,
            itemId = 2,
            amount = 3,
            price = 12.5f,
            subTotal = 37.5f,
            total = 37.5f,
            createTime = "2021-12-12 09:35:50",
            name = "Четыре сыра"
        ),
        BillItem(
            billItemId = 7,
            billId = 1,
            itemId = 1,
            amount = 2,
            price = 5.0f,
            subTotal = 10.0f,
            total = 10.0f,
            createTime = "2021-12-12 09:35:00",
            name = "Пеперони"
        ),
        BillItem(
            billItemId = 8,
            billId = 1,
            itemId = 5,
            amount = 1,
            price = 8.0f,
            subTotal = 8.0f,
            total = 8.0f,
            createTime = "2021-12-12 09:35:15",
            name = "Золотой ключик"
        ),
        BillItem(
            billItemId = 9,
            billId = 1,
            itemId = 6,
            amount = 3,
            price = 12.5f,
            subTotal = 37.5f,
            total = 37.5f,
            createTime = "2021-12-12 09:35:50",
            name = "Рачки"
        ),
        BillItem(
            billItemId = 10,
            billId = 1,
            itemId = 5,
            amount = 2,
            price = 5.0f,
            subTotal = 10.0f,
            total = 10.0f,
            createTime = "2021-12-12 09:35:00",
            name = "Золотой ключик"
        )
    )

fun mockItemGroups(): ArrayList<ItemGroup> =
    arrayListOf(
        ItemGroup(
            itemGroupId = 1,
            name = "Пицца",
            shortName = "П-ца",
            available = true
        ),
        ItemGroup(
            itemGroupId = 2,
            name = "Фрукты",
            shortName = "Фр-ты",
            available = true
        ),
        ItemGroup(
            itemGroupId = 3,
            name = "Сигареты",
            shortName = "Сиг-ты",
            available = true
        ),
        ItemGroup(
            itemGroupId = 4,
            name = "Конфеты",
            shortName = "Конф.",
            available = true
        ),
    )

fun mockItems(itemGroupId: Long): List<Item> =
    arrayListOf(
        Item(
            itemId = 1,
            itemGroupId = 1,
            name = "Пеперони",
            shorName = "Пеперони",
            available = true,
            price = Price(itemId = 1, price = 350)
        ),
        Item(
            itemId = 2,
            itemGroupId = 1,
            name = "Четыре сыра",
            shorName = "Четыре сыра",
            available = true,
            price = Price(itemId = 2, price = 360)
        ),
        Item(
            itemId = 7,
            itemGroupId = 1,
            name = "Маринара",
            shorName = "Маринара",
            available = true,
            price = Price(itemId = 2, price = 360)
        ),
        Item(
            itemId = 8,
            itemGroupId = 1,
            name = "Четыре сезона",
            shorName = "Четыре сезона",
            available = true,
            price = Price(itemId = 2, price = 360)
        ),
        Item(
            itemId = 9,
            itemGroupId = 1,
            name = "Карбонара",
            shorName = "Карбонара",
            available = true,
            price = Price(itemId = 2, price = 360)
        ),
        Item(
            itemId = 10,
            itemGroupId = 1,
            name = "Крудо",
            shorName = "Крудо",
            available = true,
            price = Price(itemId = 2, price = 360)
        ),
        Item(
            itemId = 3,
            itemGroupId = 2,
            name = "Яблоки",
            shorName = "Яблоки",
            available = true,
            price = Price(itemId = 3, price = 180)
        ),
        Item(
            itemId = 4,
            itemGroupId = 2,
            name = "Апельсины",
            shorName = "Апельсины",
            available = true,
            price = Price(itemId = 4, price = 190)
        ),
        Item(
            itemId = 5,
            itemGroupId = 4,
            name = "Золотой ключик",
            shorName = "Золотой ключик",
            available = true,
            price = Price(itemId = 5, price = 120)
        ),
        Item(
            itemId = 6,
            itemGroupId = 4,
            name = "Рачки",
            shorName = "Рачки",
            available = true,
            price = Price(itemId = 5, price = 135)
        )
    ).filter { item -> item.itemGroupId == itemGroupId }

