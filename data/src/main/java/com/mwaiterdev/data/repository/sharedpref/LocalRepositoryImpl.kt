package com.mwaiterdev.data.repository.sharedpref

import android.content.Context
import android.content.SharedPreferences
import com.mwaiterdev.domain.models.User
import com.mwaiterdev.domain.repository.LocalRepository

class LocalRepositoryImpl(context: Context) : LocalRepository {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)

    override fun saveUser(user: User): Boolean {
        return try {
            sharedPreferences.edit()
                .putLong(KEY_USER_GROUP_ID, user.userGroupId)
                .putString(KEY_USER_NAME, user.name)
                .putBoolean(KEY_USER_IS_ACTIVE, user.isActive)
                .putString(KEY_USER_GROUP_NAME, user.groupName)
                .putLong(KEY_USER_ID, user.userId)
                .apply()
            true
        } catch (err: Exception) {
            false
        }
    }

    override fun getCurrentUser(): User? {
        val userId: Long = sharedPreferences.getLong(KEY_USER_ID, 0)
        val userGroupId: Long = sharedPreferences.getLong(KEY_USER_GROUP_ID, 0)
        val name: String = sharedPreferences.getString(KEY_USER_NAME, "") ?: ""
        val isActive: Boolean = sharedPreferences.getBoolean(KEY_USER_IS_ACTIVE, false) ?: false
        val groupName: String = sharedPreferences.getString(KEY_USER_GROUP_NAME, "") ?: ""
        return User(
            userId = userId,
            userGroupId = userGroupId,
            name = name,
            isActive = isActive,
            pin = "",
            groupName = groupName
        )
    }

    companion object {
        private const val SHARED_PREFS_NAME = "shared_prefs_name"
        private const val KEY_USER_GROUP_ID = "user_group_id"
        private const val KEY_USER_NAME = "user_name"
        private const val KEY_USER_IS_ACTIVE = "user_is_active"
        private const val KEY_USER_GROUP_NAME = "user_group_name"
        private const val KEY_USER_ID = "user_id"
    }
}