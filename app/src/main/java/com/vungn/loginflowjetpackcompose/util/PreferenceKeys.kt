package com.vungn.loginflowjetpackcompose.util

import androidx.datastore.preferences.core.stringPreferencesKey

object PreferenceKeys {
    val USER_NAME = stringPreferencesKey("user_name")
    val FIRST_NAME = stringPreferencesKey("first_name")
    val LAST_NAME = stringPreferencesKey("last_name")
    val AVATAR = stringPreferencesKey("avatar")
    val EMAIL = stringPreferencesKey("email")
    val TOKEN = stringPreferencesKey("token")
}