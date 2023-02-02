package com.vungn.loginflowjetpackcompose.util

import kotlinx.coroutines.flow.MutableStateFlow

enum class LoginState {
    LoggedIn,
    NotLoggedIn
}

class UserStorage {
    private val _user: MutableStateFlow<LoginState> = MutableStateFlow(LoginState.NotLoggedIn)
    val user = _user

    fun onLoggedIn() {
        _user.value = LoginState.LoggedIn
    }
}