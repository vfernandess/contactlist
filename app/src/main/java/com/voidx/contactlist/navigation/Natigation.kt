package com.voidx.contactlist.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.composable(
    router: NavigationRouter,
    content: @Composable (NavBackStackEntry) -> Unit
) {
    composable(
        route = router.route,
        arguments = router.arguments,
        deepLinks = router.deepLinks,
        content = content
    )
}

interface NavigationRouter {

    val route: String

    val arguments: List<NamedNavArgument>
        get() = emptyList()

    val deepLinks: List<NavDeepLink>
        get() = emptyList()
}
