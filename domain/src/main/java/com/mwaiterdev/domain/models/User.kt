package com.mwaiterdev.domain.models

data class User(
    val userId: Int,
    val userGroupId: Int,
    val name: String,
    val isActive: Boolean,
    val pin : Int
)
