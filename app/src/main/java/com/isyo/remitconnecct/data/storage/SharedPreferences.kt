package com.isyo.remitconnecct.data.storage

import android.content.Context
import com.google.gson.Gson
import com.isyo.remitconnecct.model.User

class SharedPreferences(context: Context) : CorePreferences(context.applicationContext){
    private var gson: Gson = Gson()

    companion object{
        private const val USER_INFO = "key:user_info"
    }

    init {
        gson = Gson()
    }

    var userInfo: User?
        get() = gson.fromJson(
            getString(USER_INFO),
            User::class.java
        )
        set(userInfo) {
            val userInfoString = gson.toJson(userInfo)
            setAndApply(USER_INFO, userInfoString)
        }
}