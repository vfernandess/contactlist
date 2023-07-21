package com.voidx.contactlist.feature.contact.detail

import com.voidx.contactlist.data.model.Contact
import com.voidx.contactlist.navigation.NavigationRouter

object ContactDetailRouter: NavigationRouter {

    private const val contactArgName = "contact"
    private const val contactDetailRouteName = "contact-detail"

    override val route: String = "$contactDetailRouteName/{$contactArgName}"

    fun showContactDetail(contact: Contact): String = "$contactDetailRouteName/$contact"
}
