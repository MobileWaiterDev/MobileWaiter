package com.mwaiterdev.domain.repository

class RepositoryImp : Repository {
    override fun getWaitress(id: Int): com.mwaiterdev.domain.models.Waitress = mockDataWaitress()
    override fun getHalls(): List<com.mwaiterdev.domain.models.Hall> = mockDataHalls()
}

