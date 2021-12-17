package com.test_app.repository

import com.test_app.models.Hall
import com.test_app.models.Waitress

interface Repository {
    fun getWaitress(id: Int): Waitress
    fun getHalls(): List<Hall>
}