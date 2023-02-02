package com.vungn.loginflowjetpackcompose.ui.destination.home.news

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.vungn.loginflowjetpackcompose.ui.theme.LoginFlowJetpackComposeTheme

@Composable
fun NewsDestination(onBackNavigation: () -> Unit) {
    LoginFlowJetpackComposeTheme {
        NewsScreen(onBackNavigation = onBackNavigation)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsScreen(modifier: Modifier = Modifier, onBackNavigation: () -> Unit) {
    Scaffold(topBar = {
        TopAppBar(title = { Text(text = "News") }, navigationIcon = {
            IconButton(onClick = { onBackNavigation() }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack, contentDescription = "Back navigation"
                )
            }
        })
    }) { paddingValues ->
        Surface(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "News")
            }
        }

    }
}
