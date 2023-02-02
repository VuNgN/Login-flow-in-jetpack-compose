package com.vungn.loginflowjetpackcompose.ui.activity.viewmodel.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.vungn.loginflowjetpackcompose.data.model.User
import com.vungn.loginflowjetpackcompose.data.repository.UserRepository
import com.vungn.loginflowjetpackcompose.ui.activity.viewmodel.ActivityViewModel
import com.vungn.loginflowjetpackcompose.util.LoginState
import com.vungn.loginflowjetpackcompose.util.UserStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class ActivityViewModelImpl @Inject constructor(
    private val userStorage: UserStorage,
    userRepository: UserRepository
) : ActivityViewModel,
    ViewModel() {
    private val _loginState: MutableStateFlow<LoginState> = userStorage.user
    private val _user: LiveData<User?> = userRepository.userPreferenceFlow.asLiveData()

    override val loginState: MutableStateFlow<LoginState> = _loginState
    override val user: LiveData<User?> = _user
    override fun onLoggedIn() {
        userStorage.onLoggedIn()
    }
}