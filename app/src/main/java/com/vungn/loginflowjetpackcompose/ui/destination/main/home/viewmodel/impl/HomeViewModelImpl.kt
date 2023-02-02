package com.vungn.loginflowjetpackcompose.ui.destination.main.home.viewmodel.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.vungn.loginflowjetpackcompose.data.model.User
import com.vungn.loginflowjetpackcompose.data.repository.UserRepository
import com.vungn.loginflowjetpackcompose.ui.destination.main.home.viewmodel.HomeViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModelImpl @Inject constructor(userRepository: UserRepository) : ViewModel(),
    HomeViewModel {
    private val _user = userRepository.userPreferenceFlow.asLiveData()

    override val user: LiveData<User?>
        get() = _user
}