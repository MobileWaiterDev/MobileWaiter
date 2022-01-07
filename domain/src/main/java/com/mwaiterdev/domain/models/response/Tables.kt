package com.mwaiterdev.domain.models.response

import com.mwaiterdev.domain.models.ITableItem

data class Tables(val data: ArrayList<ITableItem>) : IResponseResult {}