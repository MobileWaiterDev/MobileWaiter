package com.mwaiterdev.domain.repository

import com.mwaiterdev.domain.models.User

interface LocalRepository {
    fun saveUser(user: User): Boolean
    fun getCurrentUser(): User?
}