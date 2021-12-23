package com.mwaiterdev.domain.models

data class ItemGroup(
    val itemGroupId: Long,
    val name: String,
    val shortName: String,
    val available: Boolean
) : IMenuItem