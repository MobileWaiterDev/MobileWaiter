package com.mwaiterdev.domain.models

data class Hall(
    val id: Int,
    val name: String,
    val tables: List<Table>
)
