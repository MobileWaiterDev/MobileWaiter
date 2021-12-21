package com.mwaiterdev.domain.models

data class Item(
    val itemId: Int,
    val itemGroupId: Int,
    val name: String,
    val shorName: String,
    val available: Boolean,
    val price: Price
)