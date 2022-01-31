package com.mwaiterdev.domain.models.response

import com.mwaiterdev.domain.models.BillItem

data class BillItems(
    val data: ArrayList<BillItem>,
    val needScrollToEndPosition: Boolean = false
) : IResponseResult {}