package com.mwaiterdev.domain.models

data class Waitress(
    val id: Int,
    val post: String,
    val name: String,
    val hals : List<Hall>
)
