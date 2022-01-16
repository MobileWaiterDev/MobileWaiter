package com.mwaiterdev.utils.extensions

import android.content.Context
import android.content.SharedPreferences

class SharedPreferenceHelper(val context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
    var userName: String? = null
        set(value) {
            field = value
            sharedPreferences.edit().putString(USER_NAME, value).apply()
        }
        get() = sharedPreferences.getString(USER_NAME, null)

    var halsSwitcher : Int = 0
        set(value) {
            field = value
            sharedPreferences.edit().putInt(HALL_NAME, value).apply()
        }
        get() = sharedPreferences.getInt(HALL_NAME, 0)


    var isSwitchToMine: Boolean = false
         set(value) {
             field = value
             sharedPreferences.edit().putBoolean(SWITCHER_MINE_BILLS, value).apply()
         }
    get() = sharedPreferences.getBoolean(SWITCHER_MINE_BILLS, false)

    var hallExpand : Map<Int, Boolean>? = null
        set(value){
            field = value

        }


    companion object {
        private const val APP_PREFERENCES = "App_Preferences"
        private const val SWITCHER_MINE_BILLS = "Switch_To_Mine"
        private const val USER_NAME = "User_Name"
        private const val HALL_NAME = "Hall_Name"
    }
}