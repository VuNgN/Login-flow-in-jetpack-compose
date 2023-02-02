package com.vungn.loginflowjetpackcompose.ui.destination.main.home.viewmodel

import androidx.lifecycle.LiveData
import com.vungn.loginflowjetpackcompose.data.model.User

interface HomeViewModel {
    val user: LiveData<User?>
}