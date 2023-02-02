package com.vungn.loginflowjetpackcompose.ui.destination.main.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.vungn.loginflowjetpackcompose.ui.destination.main.home.viewmodel.HomeViewModel
import com.vungn.loginflowjetpackcompose.ui.theme.LoginFlowJetpackComposeTheme
import kotlinx.coroutines.launch

@Composable
fun HomeDestination(onClickToPost: () -> Unit, onClickToNews: () -> Unit, vm: HomeViewModel) {
    val user by vm.user.observeAsState()
    LoginFlowJetpackComposeTheme {
        HomeScreen(
            onClickToPost,
            onClickToNews,
            firstName = user?.firstName,
            lastName = user?.lastName
        )
    }
}

@Composable
fun HomeScreen(
    onClickToPost: () -> Unit, onClickToNews: () -> Unit, firstName: String?, lastName: String?
) {
    val coroutineScope = rememberCoroutineScope()
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Welcome $firstName $lastName!")
            Button(onClick = {
                coroutineScope.launch {
                    onClickToPost()
                }
            }) {
                Text(text = "Go to Post")
            }
            Button(onClick = {
                coroutineScope.launch {
                    onClickToNews()
                }
            }) {
                Text(text = "Go to News")
            }
        }
    }
}
