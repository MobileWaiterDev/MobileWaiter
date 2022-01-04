package com.mwaiterdev.domain.models.response

import com.google.gson.annotations.SerializedName

data class BillsResponse(
    @SerializedName("message")
    val message: String,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("tableGroups")
    val tableGroups: List<TableGroup> = arrayListOf()
) {
    data class TableGroup(
        @SerializedName("name")
        val name: String,
        @SerializedName("tableGroupCount")
        val tableGroupCount: Int,
        @SerializedName("tableGroupId")
        val tableGroupId: Long,
        @SerializedName("tableGroupTotal")
        val tableGroupTotal: Int,
        @SerializedName("tables")
        val tables: List<Table> = arrayListOf(),
        var isExpanded: Boolean = false
    ) {
        data class Table(
            @SerializedName("askCustomers")
            val askCustomers: Boolean,
            @SerializedName("askCustomersTableGroup")
            val askCustomersTableGroup: Boolean,
            @SerializedName("bills")
            val bills: List<Bill> = arrayListOf(),
            @SerializedName("maxCustomers")
            val maxCustomers: Int,
            @SerializedName("name")
            val name: String,
            @SerializedName("printed")
            val printed: Boolean,
            @SerializedName("state")
            val state: Int,
            @SerializedName("tableGroupId")
            val tableGroupId: Long,
            @SerializedName("tableId")
            val tableId: Long
        ) {
            data class Bill(
                @SerializedName("billId")
                val billId: Long,
                @SerializedName("billItems")
                val billItems: List<BillItem> = arrayListOf(),
                @SerializedName("billNumber")
                val billNumber: Long,
                @SerializedName("clientId")
                val clientId: Long,
                @SerializedName("createdByUserId")
                val createdByUserId: Long,
                @SerializedName("createdByUserName")
                val createdByUserName: String,
                @SerializedName("createTime")
                val createTime: String,
                @SerializedName("customers")
                val customers: Int,
                @SerializedName("name")
                val name: String,
                @SerializedName("subtotal")
                val subtotal: Float,
                @SerializedName("tableId")
                val tableId: Long,
                @SerializedName("TableName")
                val tableName: String,
                @SerializedName("total")
                val total: Float,
                @SerializedName("userId")
                val userId: Long
            ) {
                data class BillItem(
                    @SerializedName("name")
                    val name: String
                )
            }
        }
    }
}