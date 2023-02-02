package com.vungn.loginflowjetpackcompose.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.vungn.loginflowjetpackcompose.util.PreferenceKeys
import javax.inject.Inject

class LoginRepository @Inject constructor(private val dataStore: DataStore<Preferences>) {
    suspend fun login(username: String, password: String): Boolean {
        return if (username.trim() == "vu" && password.trim() == "vu") {
            dataStore.edit { pref ->
                pref[PreferenceKeys.USER_NAME] = username.trim()
                pref[PreferenceKeys.FIRST_NAME] = "Vũ"
                pref[PreferenceKeys.LAST_NAME] = "Nguyễn"
                pref[PreferenceKeys.AVATAR] = ""
                pref[PreferenceKeys.EMAIL] = "vu.nguyeenngoc@gmail.com"
                pref[PreferenceKeys.TOKEN] = "0000"
            }
            true
        } else false
    }
}