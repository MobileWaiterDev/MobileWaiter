package com.mwaiterdev.domain.models.response

import com.google.gson.annotations.SerializedName

data class ItemsResponse(
    @SerializedName("items")
    val items: List<Item>?,
    @SerializedName("message")
    val message: String,
    @SerializedName("success")
    val success: Boolean
) {
    data class Item(
        @SerializedName("available")
        val available: Boolean,
        @SerializedName("itemGroupId")
        val itemGroupId: Long,
        @SerializedName("itemId")
        val itemId: Long,
        @SerializedName("name")
        val name: String,
        @SerializedName("price")
        val price: Float,
        @SerializedName("shortName")
        val shortName: String,
        @SerializedName("favourite")
        val favourite: Int
    )
}