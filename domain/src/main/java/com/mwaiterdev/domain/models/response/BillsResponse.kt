package com.mwaiterdev.domain.models.response

import com.google.gson.annotations.SerializedName

data class BillsResponse(
    @SerializedName("message")
    val message: String,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("tableGroups")
    var tableGroups: List<TableGroup>?
) {
    data class TableGroup(
        @SerializedName("name")
        val name: String,
        @SerializedName("tableGroupCount")
        val tableGroupCount: Int,
        @SerializedName("tableGroupId")
        val tableGroupId: Long,
        @SerializedName("tableGroupTotal")
        val tableGroupTotal: Float,
        @SerializedName("tables")
        var tables: List<Table>?,
        var isExpanded: Boolean = true
    ) {
        data class Table(
            @SerializedName("askCustomers")
            val askCustomers: Boolean,
            @SerializedName("askCustomersTableGroup")
            val askCustomersTableGroup: Boolean,
            @SerializedName("bills")
            var bills: List<Bill>?,
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
                val billItems: List<BillItem>?,
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