package com.mwaiterdev.domain.models

import com.mwaiterdev.domain.models.response.IResponseResult

data class User(
    val userId: Long,
    val userGroupId: Long,
    val name: String,
    val isActive: Boolean,
    val pin: String,
    val groupName: String
) : IResponseResult