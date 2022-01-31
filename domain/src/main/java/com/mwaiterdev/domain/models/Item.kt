package com.mwaiterdev.domain.models

data class Item(
    val itemId: Long,
    val itemGroupId: Long,
    val name: String,
    val shorName: String,
    val available: Boolean,
    val price: Price,
    val favourite: Int,
    val bgColor: String = "#FFFFFF",
    val textColor: String = "#000000"
) : IMenuItem