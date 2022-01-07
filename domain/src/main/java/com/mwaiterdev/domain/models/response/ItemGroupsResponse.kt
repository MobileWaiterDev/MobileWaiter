package com.mwaiterdev.domain.models.response

import com.google.gson.annotations.SerializedName

data class ItemGroupsResponse(
    @SerializedName("itemGroups")
    val itemGroups: List<ItemGroup>?,
    @SerializedName("message")
    val message: String,
    @SerializedName("success")
    val success: Boolean
) {
    data class ItemGroup(
        @SerializedName("available")
        val available: Boolean,
        @SerializedName("itemGroupId")
        val itemGroupId: Long,
        @SerializedName("name")
        val name: String,
        @SerializedName("shortName")
        val shortName: String
    )
}