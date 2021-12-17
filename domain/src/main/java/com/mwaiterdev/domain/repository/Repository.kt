package com.mwaiterdev.domain.repository

import com.mwaiterdev.domain.models.Hall
import com.mwaiterdev.domain.models.Waitress

interface Repository {
    fun getWaitress(id: Int): Waitress
    fun getHalls(): List<Hall>
}