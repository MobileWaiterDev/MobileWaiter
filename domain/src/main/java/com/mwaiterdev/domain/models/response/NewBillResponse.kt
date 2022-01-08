package com.mwaiterdev.domain.models.response

import com.google.gson.annotations.SerializedName

data class NewBillResponse(
    @SerializedName("billId")
    val billId: Long,
    @SerializedName("message")
    val message: String,
    @SerializedName("success")
    val success: Boolean
)