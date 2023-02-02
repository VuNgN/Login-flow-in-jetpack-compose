package com.vungn.loginflowjetpackcompose

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.vungn.loginflowjetpackcompose.ui.destination.login.LoginScreen
import com.vungn.loginflowjetpackcompose.ui.destination.main.MainScaffold
import com.vungn.loginflowjetpackcompose.ui.theme.LoginFlowJetpackComposeTheme
import org.junit.Rule
import org.junit.Test

class MyComposeTest {
    @get:Rule
    val composeTestRule = createComposeRule()
    // use createAndroidComposeRule<YourActivity>() if you need access to
    // an activity

    @Test
    fun loginScreenTest() {
        // Start the app
        composeTestRule.setContent {
            LoginFlowJetpackComposeTheme {
                LoginScreen(login = { _, _ -> })
            }
        }

        composeTestRule.onNodeWithText("Username").assertExists().assertHasClickAction()
            .performClick().assertIsFocused()
        composeTestRule.onNodeWithText("Password").assertExists().assertHasClickAction()
            .performClick().assertIsFocused()
        composeTestRule.onNodeWithText("Login").assertExists().assertHasClickAction()
    }

    @Test
    fun mainScreenTest() {
        composeTestRule.setContent {
            MainScaffold(navigateToPost = { }, navigateToNews = { })
        }

        composeTestRule.onNode(hasContentDescription("Navigation bar")).assertExists()
    }
}