package com.vungn.loginflowjetpackcompose.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector

object NavBarItems {
    val navBarItems = listOf(
        NavBarItem(
            "Home",
            Routes.Home.route,
            Icons.Filled.Home
        ),
        NavBarItem(
            "Study",
            Routes.Study.route,
            Icons.Filled.Star
        ),
        NavBarItem(
            "Utilities",
            Routes.Utility.route,
            Icons.Filled.Settings
        ),
        NavBarItem(
            "Profile",
            Routes.Individual.route,
            Icons.Filled.Person
        ),
    )
}

data class NavBarItem(val title: String, val route: String, val icon: ImageVector)