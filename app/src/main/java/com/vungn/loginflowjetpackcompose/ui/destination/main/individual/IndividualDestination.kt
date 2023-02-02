package com.vungn.loginflowjetpackcompose.ui.destination.main.individual

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.vungn.loginflowjetpackcompose.ui.theme.LoginFlowJetpackComposeTheme

@Composable
fun IndividualDestination() {
    LoginFlowJetpackComposeTheme {
        IndividualScreen()
    }
}

@Composable
fun IndividualScreen() {
    Surface(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = "Individual")
        }
    }
}
