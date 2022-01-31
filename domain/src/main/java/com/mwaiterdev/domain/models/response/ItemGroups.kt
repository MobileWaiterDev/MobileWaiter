package com.mwaiterdev.domain.models.response

import com.mwaiterdev.domain.models.IMenuItem

data class ItemGroups(val data: ArrayList<IMenuItem>) : IResponseResult {}