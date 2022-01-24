package com.mwaiterdev.domain.models.response

import com.mwaiterdev.domain.models.User

data class Users(val data: ArrayList<User>) : IResponseResult {}