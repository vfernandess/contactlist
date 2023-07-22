package com.voidx.contactlist.feature.contact.list

import com.voidx.contactlist.feature.contact.ContactNavigation
import com.voidx.contactlist.navigation.NavigationRouter

object ContactListRouter: NavigationRouter {
    override val route: String = "${ContactNavigation.route}/contact-list"
}
