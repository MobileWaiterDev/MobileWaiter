package com.test_app.repository

import com.test_app.models.*

class RepositoryImp : Repository {
    override fun getWaitress(id: Int): Waitress = mockDataWaitress()
    override fun getHalls(): List<Hall> = mockDataHalls()
}

