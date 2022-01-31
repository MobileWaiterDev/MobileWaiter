package com.mwaiterdev.domain.models.response

import com.google.gson.annotations.SerializedName

data class TableGroupsResponse(
    @SerializedName("tableGroups")
    val tableGroups: List<TableGroup>?,
    @SerializedName("message")
    val message: String,
    @SerializedName("success")
    val success: Boolean
) {
    data class TableGroup(
        @SerializedName("name")
        val name: String,
        @SerializedName("tableGroupId")
        val tableGroupId: Long
    )
}