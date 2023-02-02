package com.vungn.loginflowjetpackcompose.util

import androidx.navigation.NavDeepLink
import androidx.navigation.navDeepLink
import com.vungn.loginflowjetpackcompose.util.Const.DEEP_LINKS_SCHEME
import com.vungn.loginflowjetpackcompose.util.Const.NEWS_ROUTE
import com.vungn.loginflowjetpackcompose.util.Const.POST_ROUTE


sealed interface Route {
    val route: String
}

sealed class Routes {
    object Login : Route {
        override val route: String
            get() = Const.LOGIN_ROUTE
    }

    object Home : Route {
        override val route: String
            get() = Const.HOME_ROUTE
    }

    object Study : Route {
        override val route: String
            get() = Const.STUDY_ROUTE
    }

    object Utility : Route {
        override val route: String
            get() = Const.UTILITY_ROUTE
    }

    object Individual : Route {
        override val route: String
            get() = Const.INDIVIDUAL_ROUTE
    }

    object Post : Route {
        override val route: String
            get() = POST_ROUTE
        private val deepLink: String
            get() = "$DEEP_LINKS_SCHEME://${POST_ROUTE}/"
        val deepLinks: List<NavDeepLink>
            get() = listOf(navDeepLink { uriPattern = deepLink })
    }

    object News : Route {
        override val route: String
            get() = NEWS_ROUTE
    }

    object MainGraph : Route {
        override val route: String
            get() = Const.MAIN_GRAPH_ROUTE
    }

    object LoginGraph : Route {
        override val route: String
            get() = Const.LOGIN_GRAPH_ROUTE
    }

    object HomeGraph : Route {
        override val route: String
            get() = Const.HOME_GRAPH_ROUTE
    }

    object MainHost : Route {
        override val route: String
            get() = Const.MAIN_HOST_ROUTE
    }
}
