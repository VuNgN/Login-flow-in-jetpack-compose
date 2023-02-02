package com.vungn.loginflowjetpackcompose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.vungn.loginflowjetpackcompose.ui.activity.viewmodel.ActivityViewModel
import com.vungn.loginflowjetpackcompose.ui.activity.viewmodel.impl.ActivityViewModelImpl
import com.vungn.loginflowjetpackcompose.ui.destination.home.news.NewsDestination
import com.vungn.loginflowjetpackcompose.ui.destination.home.post.PostDestination
import com.vungn.loginflowjetpackcompose.ui.destination.login.LoginDestination
import com.vungn.loginflowjetpackcompose.ui.destination.login.viewmodel.impl.LoginViewModelImpl
import com.vungn.loginflowjetpackcompose.ui.destination.main.MainHost
import com.vungn.loginflowjetpackcompose.ui.theme.LoginFlowJetpackComposeTheme
import com.vungn.loginflowjetpackcompose.util.LoginState
import com.vungn.loginflowjetpackcompose.util.Routes
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val vm: ActivityViewModel by viewModels<ActivityViewModelImpl>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm.user.observe(this) {
            Log.d(TAG, "Stored user -> $it")
            if (it != null) {
                vm.onLoggedIn()
            }
        }
        setContent {
            LoginFlowJetpackComposeTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    RootNavHost()
                }
            }
        }
    }

    @Composable
    fun RootNavHost() {
        val navController = rememberNavController()
        LaunchedEffect(key1 = true) {
            vm.loginState.collect { state ->
                Log.d(TAG, "Login state -> $state")
                if (state == LoginState.NotLoggedIn) navController.navigate(Routes.LoginGraph.route)
            }
        }
        NavHost(navController = navController, startDestination = Routes.MainGraph.route) {
            loginGraph(navController)
            mainGraph(navController)
            homeGraph(navController)
        }
    }

    private fun NavGraphBuilder.loginGraph(navController: NavHostController) {
        navigation(startDestination = Routes.Login.route, route = Routes.LoginGraph.route) {
            composable(route = Routes.Login.route) {
                val loginViewModel = hiltViewModel<LoginViewModelImpl>()
                LoginDestination(vm = loginViewModel, onLoggedIn = { navController.popBackStack() })
            }
        }
    }

    private fun NavGraphBuilder.mainGraph(navController: NavHostController) {
        navigation(startDestination = Routes.MainHost.route, route = Routes.MainGraph.route) {
            composable(route = Routes.MainHost.route) {
                MainHost(navigateToPost = { navController.navigate(Routes.Post.route) },
                    navigateToNews = { navController.navigate(Routes.News.route) })
            }
        }
    }

    private fun NavGraphBuilder.homeGraph(navController: NavHostController) {
        navigation(startDestination = Routes.Post.route, route = Routes.HomeGraph.route) {
            composable(route = Routes.Post.route, deepLinks = Routes.Post.deepLinks) {
                PostDestination {
                    navController.popBackStack()
                }
            }
            composable(route = Routes.News.route) {
                NewsDestination {
                    navController.popBackStack()
                }
            }
        }
    }

    companion object {
        private val TAG = MainActivity::class.simpleName
    }
}
