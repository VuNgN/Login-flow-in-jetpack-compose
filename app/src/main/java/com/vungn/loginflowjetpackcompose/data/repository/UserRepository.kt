package com.vungn.loginflowjetpackcompose.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import com.vungn.loginflowjetpackcompose.data.model.User
import com.vungn.loginflowjetpackcompose.util.PreferenceKeys
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class UserRepository @Inject constructor(dataStore: DataStore<Preferences>) {
    private val _userPreferenceFlow = dataStore.data.catch { exception ->
        if (exception is IOException) {
            emit(emptyPreferences())
        } else {
            throw exception
        }
    }.map { pref: Preferences ->
        val username: String? = pref[PreferenceKeys.USER_NAME]
        val firstName: String? = pref[PreferenceKeys.FIRST_NAME]
        val lastName: String? = pref[PreferenceKeys.LAST_NAME]
        val avatar: String? = pref[PreferenceKeys.AVATAR]
        val email: String? = pref[PreferenceKeys.EMAIL]
        val token: String? = pref[PreferenceKeys.TOKEN]
        if (username == null || firstName == null || lastName == null || avatar == null || email == null || token == null) {
            null
        } else {
            User(
                username = username,
                firstName = firstName,
                lastName = lastName,
                avatar = avatar,
                email = email,
                token = token
            )
        }
    }

    val userPreferenceFlow = _userPreferenceFlow
}