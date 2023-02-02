package com.vungn.loginflowjetpackcompose.ui.destination.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.vungn.loginflowjetpackcompose.ui.destination.main.home.HomeDestination
import com.vungn.loginflowjetpackcompose.ui.destination.main.home.viewmodel.impl.HomeViewModelImpl
import com.vungn.loginflowjetpackcompose.ui.destination.main.individual.IndividualDestination
import com.vungn.loginflowjetpackcompose.ui.destination.main.study.StudyDestination
import com.vungn.loginflowjetpackcompose.ui.destination.main.utility.UtilityFragment
import com.vungn.loginflowjetpackcompose.ui.theme.LoginFlowJetpackComposeTheme
import com.vungn.loginflowjetpackcompose.util.NavBarItems.navBarItems
import com.vungn.loginflowjetpackcompose.util.Routes
import kotlinx.coroutines.launch

@Composable
fun MainHost(navigateToPost: () -> Unit, navigateToNews: () -> Unit) {
    LoginFlowJetpackComposeTheme {
        MainScaffold(navigateToPost = navigateToPost, navigateToNews = navigateToNews)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScaffold(
    modifier: Modifier = Modifier,
    navigateToPost: () -> Unit,
    navigateToNews: () -> Unit
) {
    val navController = rememberNavController()
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    Scaffold(modifier = modifier, bottomBar = {
        NavigationBar(modifier = Modifier.semantics { contentDescription = "Navigation bar" }) {
            navBarItems.forEach { screen ->
                NavigationBarItem(selected = currentBackStackEntry?.destination?.hierarchy?.any {
                    it.route == screen.route
                } == true, onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }, label = {
                    Text(text = screen.title)
                }, alwaysShowLabel = true, icon = {
                    Icon(imageVector = screen.icon, contentDescription = screen.title)
                })
            }
        }
    }) { paddingValues ->
        MainNavHost(
            modifier = Modifier.padding(paddingValues),
            navController = navController,
            navigateToPost = navigateToPost,
            navigateToNews = navigateToNews
        )
    }
}

@Composable
fun MainNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    navigateToPost: () -> Unit,
    navigateToNews: () -> Unit
) {
    NavHost(
        modifier = modifier, navController = navController, startDestination = Routes.Home.route
    ) {
        composable(route = Routes.Home.route) {
            val vm = hiltViewModel<HomeViewModelImpl>()
            val coroutineScope = rememberCoroutineScope()
            HomeDestination(
                vm = vm,
                onClickToPost = {
                    coroutineScope.launch {
                        navigateToPost()
                    }
                },
                onClickToNews = {
                    coroutineScope.launch {
                        navigateToNews()
                    }
                })
        }
        composable(route = Routes.Study.route) {
            StudyDestination()
        }
        composable(route = Routes.Utility.route) {
            UtilityFragment()
        }
        composable(route = Routes.Individual.route) {
            IndividualDestination()
        }
    }
}
