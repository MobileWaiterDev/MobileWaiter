package com.mwaiterdev.domain.models.response


import com.google.gson.annotations.SerializedName

data class BillsInfoResponse(
    @SerializedName("billInfo")
    val billInfo: BillInfo,
    @SerializedName("message")
    val message: String,
    @SerializedName("success")
    val success: Boolean
) {
    data class BillInfo(
        @SerializedName("billId")
        val billId: Long,
        @SerializedName("billNumber")
        val billNumber: Long,
        @SerializedName("clientId")
        val clientId: Long,
        @SerializedName("countItems")
        val countItems: Int,
        @SerializedName("createTime")
        val createTime: String,
        @SerializedName("createdByUserId")
        val createdByUserId: Long,
        @SerializedName("customers")
        val customers: Int,
        @SerializedName("name")
        val name: String,
        @SerializedName("printed")
        val printed: Int,
        @SerializedName("subtotal")
        val subtotal: Float,
        @SerializedName("tableId")
        val tableId: Int,
        @SerializedName("total")
        val total: Float,
        @SerializedName("userGroupId")
        val userGroupId: Int,
        @SerializedName("userName")
        val userName: String
    )
}