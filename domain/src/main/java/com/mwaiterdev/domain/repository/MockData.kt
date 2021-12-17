package com.mwaiterdev.domain.repository

import com.mwaiterdev.domain.models.*

fun mockDataWaitress(): Waitress =
    Waitress(
        1,
        "Младший офицант",
        "Гаврил Гаврилов",
        listOf<Hall>(
            Hall(
                11,
                "Зал 1",
                listOf<Table>(
                    Table(
                        111, 11, 2, true, 1,
                        Bills(
                            1111,
                            1,
                            listOf(
                                Orders(1111, "Бифштекс", 500f),
                                Orders(1112, "Картофель", 200f),
                                Orders(1113, "Чай", 150f)
                            )
                        )
                    ),
                    Table(
                        222, 11, 2, true, 1,
                        Bills(
                            2222,
                            1,
                            listOf(
                                Orders(1111, "Мясо", 500f),
                                Orders(1112, "Картофель", 200f),
                                Orders(1113, "Кофе", 150f)
                            )
                        )
                    )
                )
            ),
            Hall(
                22,
                "Зал 2",
                listOf<Table>(
                    Table(
                        333, 22, 2, true, 1,
                        Bills(
                            1111,
                            1,
                            listOf(
                                Orders(1111, "Бифштекс", 500f),
                                Orders(1112, "Картофель", 200f),
                                Orders(1113, "Чай", 150f)
                            )
                        )
                    ),
                    Table(
                        222, 11, 2, true, 1,
                        Bills(
                            2222,
                            1,
                            listOf(
                                Orders(1111, "Мясо", 500f),
                                Orders(1112, "Картофель", 200f),
                                Orders(1113, "Кофе", 150f)
                            )
                        )
                    )
                )
            )
        )
    )

fun mockDataHalls() = listOf<Hall>(
    Hall(
        11,
        "Зал 1",
        listOf<Table>(
            Table(
                111, 11, 2, true, 1,
                Bills(
                    1111,
                    1,
                    listOf(
                        Orders(1111, "Бифштекс", 500f),
                        Orders(1112, "Картофель", 200f),
                        Orders(1113, "Чай", 150f)
                    )
                )
            ),
            Table(
                222, 11, 2, true, 1,
                Bills(
                    2222,
                    1,
                    listOf(
                        Orders(1111, "Мясо", 500f),
                        Orders(1112, "Картофель", 200f),
                        Orders(1113, "Кофе", 150f)
                    )
                )
            ),

            Table(
                222, 11, 2, true, 1,
                Bills(
                    2222,
                    1,
                    listOf(
                        Orders(1111, "Мясо", 500f),
                        Orders(1112, "Картофель", 200f),
                        Orders(1113, "Кофе", 150f)
                    )
                )
            )

        )
    ),
    Hall(
        22,
        "Зал 2",
        listOf<Table>(
            Table(
                333, 22, 2, true, 1,
                Bills(
                    1111,
                    1,
                    listOf(
                        Orders(1111, "Бифштекс", 500f),
                        Orders(1112, "Картофель", 200f),
                        Orders(1113, "Чай", 150f)
                    )
                )
            ),
            Table(
                222, 11, 2, true, 1,
                Bills(
                    2222,
                    1,
                    listOf(
                        Orders(1111, "Мясо", 500f),
                        Orders(1112, "Картофель", 200f),
                        Orders(1113, "Кофе", 150f)
                    )
                )
            ),
            Table(
                222, 11, 2, true, 1,
                Bills(
                    2222,
                    1,
                    listOf(
                        Orders(1111, "Мясо", 500f),
                        Orders(1112, "Картофель", 200f),
                        Orders(1113, "Кофе", 150f)
                    )
                )
            )
        )
    ),
    Hall(
        22,
        "Зал 3",
        listOf<Table>(
            Table(
                333, 22, 2, true, 1,
                Bills(
                    1111,
                    1,
                    listOf(
                        Orders(1111, "Бифштекс", 500f),
                        Orders(1112, "Картофель", 200f),
                        Orders(1113, "Чай", 150f)
                    )
                )
            ),
            Table(
                222, 11, 2, true, 1,
                Bills(
                    2222,
                    1,
                    listOf(
                        Orders(1111, "Мясо", 500f),
                        Orders(1112, "Картофель", 200f),
                        Orders(1113, "Кофе", 150f)
                    )
                )
            ),
            Table(
                222, 11, 2, true, 1,
                Bills(
                    2222,
                    1,
                    listOf(
                        Orders(1111, "Мясо", 500f),
                        Orders(1112, "Картофель", 200f),
                        Orders(1113, "Кофе", 150f)
                    )
                )
            )
        )
    ),
)