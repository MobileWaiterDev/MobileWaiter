package com.mwaiterdev.domain.models

data class Table(
    val id: Int,
    val idHall : Int,
    val countOfGuests: Int,
    val isReserved: Boolean,
    val waitressId: Int?,
    val bill: Bills?
)
