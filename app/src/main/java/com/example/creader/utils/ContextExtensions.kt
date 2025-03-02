package com.example.creader.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager


val Context.defaultSharedPreferences: SharedPreferences
    get() = PreferenceManager.getDefaultSharedPreferences(this)

fun Context.getPreString(key: String, defValue: String? = null) =
    defaultSharedPreferences.getString(key, defValue)

fun Context.getPreInt(key: String, defValue: Int = 0) =
    defaultSharedPreferences.getInt(key, defValue)

