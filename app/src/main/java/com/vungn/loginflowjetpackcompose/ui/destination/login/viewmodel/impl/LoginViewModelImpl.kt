package com.vungn.loginflowjetpackcompose.ui.destination.login.viewmodel.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vungn.loginflowjetpackcompose.data.repository.LoginRepository
import com.vungn.loginflowjetpackcompose.ui.destination.login.viewmodel.LoginViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModelImpl @Inject constructor(
    private val loginRepository: LoginRepository,
) : ViewModel(),
    LoginViewModel {
    private val _isLoggedIn: MutableStateFlow<Boolean> = MutableStateFlow(false)
    override val isLoggedIn: MutableStateFlow<Boolean> = _isLoggedIn

    override fun login(username: String, password: String) {
        viewModelScope.launch {
            if (loginRepository.login(username, password)) {
                _isLoggedIn.emit(true)
            } else {
                _isLoggedIn.emit(false)
            }
        }
    }
}