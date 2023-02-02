package com.vungn.loginflowjetpackcompose.ui.destination.login.viewmodel

import kotlinx.coroutines.flow.MutableStateFlow

interface LoginViewModel {
    val isLoggedIn: MutableStateFlow<Boolean>
    fun login(username: String, password: String)
}