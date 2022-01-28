package com.mwaiterdev.domain.models

data class ItemGroup(
    val itemGroupId: Long,
    val name: String,
    val shortName: String,
    val available: Boolean,
    val bgColor: String = "#FFFFFF",
    val textColor: String = "#000000"
) : IMenuItem