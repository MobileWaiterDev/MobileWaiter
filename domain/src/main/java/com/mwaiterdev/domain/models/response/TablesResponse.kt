package com.mwaiterdev.domain.models.response

import com.google.gson.annotations.SerializedName

data class TablesResponse(
    @SerializedName("message")
    val message: String,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("tableGroups")
    val tableGroups: List<TableGroup> = arrayListOf()
) {
    data class TableGroup(
        @SerializedName("askCustomers")
        val askCustomers: Boolean,
        @SerializedName("name")
        val name: String,
        @SerializedName("tableGroupId")
        val tableGroupId: Long,
        @SerializedName("tables")
        val tables: List<Table> = arrayListOf()
    ) {
        data class Table(
            @SerializedName("maxCustomers")
            val maxCustomers: Int,
            @SerializedName("name")
            val name: String,
            @SerializedName("state")
            val state: Int,
            @SerializedName("tableGroupId")
            val tableGroupId: Long,
            @SerializedName("tableId")
            val tableId: Long,
            @SerializedName("askCustomers")
            val askCustomers: Boolean
        )
    }
}