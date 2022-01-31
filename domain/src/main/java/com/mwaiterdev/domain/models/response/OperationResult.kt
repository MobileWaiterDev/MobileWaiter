package com.mwaiterdev.domain.models.response

import com.google.gson.annotations.SerializedName

data class OperationResult(
    @SerializedName("message")
    val message: String,
    @SerializedName("success")
    val success: Boolean
)