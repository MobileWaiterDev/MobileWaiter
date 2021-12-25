package com.mwaiterdev.domain.models.response

import com.google.gson.annotations.SerializedName

data class BillItemsResponse(
    @SerializedName("billItems")
    val billItems: List<BillItem> = arrayListOf(),
    @SerializedName("message")
    val message: String,
    @SerializedName("success")
    val success: Boolean
) {
    data class BillItem(
        @SerializedName("amount")
        val amount: Float,
        @SerializedName("available")
        val available: Boolean,
        @SerializedName("billId")
        val billId: Long,
        @SerializedName("billItemId")
        val billItemId: Long,
        @SerializedName("createTime")
        val createTime: String,
        @SerializedName("groupName")
        val groupName: String,
        @SerializedName("itemGroupAvailable")
        val itemGroupAvailable: Boolean,
        @SerializedName("itemGroupId")
        val itemGroupId: Long,
        @SerializedName("itemId")
        val itemId: Long,
        @SerializedName("name")
        val name: String,
        @SerializedName("price")
        val price: Float,
        @SerializedName("subtotal")
        val subtotal: Float,
        @SerializedName("total")
        val total: Float
    )
}