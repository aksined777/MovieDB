package com.themoviedb.data

import android.content.Context

class Prefs(private val context: Context) {

    private val SETTING = "setting"
    private val LANGUAGE = "language_switch"

    private fun getSharedPreferences(prefsName: String) =
        context.getSharedPreferences(prefsName, Context.MODE_PRIVATE)

    private val programSetting by lazy { getSharedPreferences(SETTING) }

    var language: String
        get() = programSetting.getString(LANGUAGE, "en") ?: "en"
        set(value) {
            programSetting.edit().putString(LANGUAGE, value).apply()
        }
}