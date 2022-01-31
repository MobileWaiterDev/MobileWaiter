package com.mwaiterdev.domain.models.response

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("message")
    val message: String,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("user")
    val user: List<User>?
) {
    data class User(
        @SerializedName("active")
        val active: Boolean,
        @SerializedName("name")
        val name: String,
        @SerializedName("pin")
        val pin: String,
        @SerializedName("userGroup")
        val userGroup: String,
        @SerializedName("userGroupId")
        val userGroupId: Long,
        @SerializedName("userId")
        val userId: Long
    )
}