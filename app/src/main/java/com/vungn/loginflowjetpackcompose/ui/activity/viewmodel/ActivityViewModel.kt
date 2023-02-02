package com.vungn.loginflowjetpackcompose.ui.activity.viewmodel

import androidx.lifecycle.LiveData
import com.vungn.loginflowjetpackcompose.data.model.User
import com.vungn.loginflowjetpackcompose.util.LoginState
import kotlinx.coroutines.flow.MutableStateFlow

interface ActivityViewModel {
    val loginState: MutableStateFlow<LoginState>
    val user: LiveData<User?>
    fun onLoggedIn()
}