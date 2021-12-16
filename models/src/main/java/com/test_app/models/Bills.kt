package com.test_app.models

data class Bills(
    val id: Int,
    val waitressId: Int,
    val orders: List<Orders>
)
