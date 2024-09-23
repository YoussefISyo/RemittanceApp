package com.isyo.remitconnecct.data.storage

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

open class CorePreferences protected constructor(context: Context) {

    private val defaultSharedPreferences: SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(context)

    open fun getString(key: String?): String? {
        return defaultSharedPreferences.getString(key, null)
    }

    open fun setAndApply(
        key: String?,
        value: String?
    ) {
        defaultSharedPreferences.edit().putString(key, value).apply()
    }
}