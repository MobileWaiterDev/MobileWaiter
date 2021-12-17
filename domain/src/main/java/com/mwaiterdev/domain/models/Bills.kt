package com.mwaiterdev.domain.models

data class Bills(
    val id: Int,
    val waitressId: Int,
    val orders: List<Orders>
)
