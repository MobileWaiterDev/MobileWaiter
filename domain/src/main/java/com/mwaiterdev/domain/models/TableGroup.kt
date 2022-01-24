package com.mwaiterdev.domain.models

import com.mwaiterdev.domain.models.response.BillsResponse

data class TableGroup(
    val tableGroupId: Long,
    val name: String,
    val askCustomers: Boolean,
    val tables: List<BillsResponse.TableGroup.Table>?,
    var isExpanded: Boolean = false
)
