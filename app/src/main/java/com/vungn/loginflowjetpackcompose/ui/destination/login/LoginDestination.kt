package com.vungn.loginflowjetpackcompose.ui.destination.login

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.vungn.loginflowjetpackcompose.ui.component.MyOutlinedTextField
import com.vungn.loginflowjetpackcompose.ui.destination.login.viewmodel.LoginViewModel
import com.vungn.loginflowjetpackcompose.ui.theme.LoginFlowJetpackComposeTheme
import kotlinx.coroutines.launch

@Composable
fun LoginDestination(vm: LoginViewModel, onLoggedIn: () -> Unit) {
    LaunchedEffect(key1 = true) {
        vm.isLoggedIn.collect {
            if (it)
                onLoggedIn()
        }
    }
    LoginFlowJetpackComposeTheme {
        LoginScreen(modifier = Modifier.fillMaxSize(), login = { username, password ->
            vm.login(username, password)
        })
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LoginScreen(modifier: Modifier = Modifier, login: (String, String) -> Unit) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val kc = LocalSoftwareKeyboardController.current
    val coroutineScope = rememberCoroutineScope()
    ConstraintLayout(modifier = modifier.padding(20.dp)) {
        val (mainLayout, signature) = createRefs()
        Surface(modifier = Modifier
            .constrainAs(mainLayout) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                width = Dimension.matchParent
                height = Dimension.preferredWrapContent
            }
            .border(
                width = 2.dp,
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(20.dp)
            ), shape = RoundedCornerShape(20.dp)) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                MyOutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = username,
                    hint = "Username",
                    onValueChange = { username = it },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email, imeAction = ImeAction.Next
                    )
                )
                MyOutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = password,
                    onValueChange = { password = it },
                    hint = "Password",
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password, imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(onDone = {
                        login(username, password)
                        kc?.hide()
                    }),
                    isPassword = true
                )
                Button(modifier = Modifier
                    .padding(top = 20.dp)
                    .heightIn(min = 45.dp), onClick = {
                    coroutineScope.launch {
                        login(username, password)
                    }
                }) {
                    Text(text = "Login")
                }
            }
        }
        Text(
            modifier = Modifier
                .constrainAs(signature) {
                    bottom.linkTo(mainLayout.bottom)
                    end.linkTo(mainLayout.end)
                }
                .padding(end = 20.dp, bottom = 10.dp),
            text = "Nguyễn Ngọc Vũ",
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLoginFragment() {
    LoginFlowJetpackComposeTheme {
        LoginScreen(login = { _, _ -> })
    }
}
