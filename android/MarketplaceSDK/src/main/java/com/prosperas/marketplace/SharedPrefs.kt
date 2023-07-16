package com.prosperas.marketplace

import android.content.Context
import android.content.SharedPreferences

class SharedPrefs(context: Context) {

    private var sharePref: SharedPreferences = context.getSharedPreferences("sessionData", Context.MODE_PRIVATE)
    var editor: SharedPreferences.Editor = sharePref.edit()

    fun setPrefString(key: String, value: String?) {
        editor.putString(key, value)
        editor.apply()
    }
    fun getPrefString(key: String?, toString: String): String? {
        return sharePref.getString(key, "")
    }
    fun setPrefBoolean(key: String?, value: Boolean) {
        editor.putBoolean(key, value)
        editor.apply()
    }
    fun getPrefBoolean(key: String?): Boolean {
        return sharePref.getBoolean(key, false)
    }

}