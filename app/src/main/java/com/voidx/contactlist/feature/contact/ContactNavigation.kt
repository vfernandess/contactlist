package com.voidx.contactlist.feature.contact

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.voidx.contactlist.feature.contact.detail.ContactDetailRouter
import com.voidx.contactlist.feature.contact.detail.ui.ContactDetailScreen
import com.voidx.contactlist.feature.contact.list.ContactListRouter
import com.voidx.contactlist.feature.contact.list.ui.ContactListScreen
import com.voidx.contactlist.navigation.NavigationRouter
import com.voidx.contactlist.navigation.composable

object ContactNavigation : NavigationRouter {

    override val route: String = "contacts"

    fun NavGraphBuilder.contacts(controller: NavController) {
        navigation(
            startDestination = ContactListRouter.route,
            route = ContactNavigation.route
        ) {
            composable(router = ContactListRouter) {
                ContactListScreen(
                    onContactSelected = {
                        val route = ContactDetailRouter.showContactDetail(it)
                        controller.navigate(route)
                    }
                )
            }

            composable(router = ContactDetailRouter) {
                ContactDetailScreen()
            }
        }
    }
}
