package com.voidx.contactlist.feature.contact.detail

import android.os.Bundle
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.voidx.contactlist.data.model.Contact
import com.voidx.contactlist.feature.contact.ContactNavigation
import com.voidx.contactlist.navigation.NavigationRouter
import kotlinx.serialization.json.Json

object ContactDetailRouter: NavigationRouter {

    const val contactArgName = "contact"
    private const val contactDetailRouteName = "contact-detail"

    override val route: String = "${ContactNavigation.route}/$contactDetailRouteName/{$contactArgName}"

    override val arguments: List<NamedNavArgument>
        get() = listOf(
            navArgument(contactArgName) { type = ContactType }
        )

    fun showContactDetail(contact: Contact): String {
        val encodedContact = Json.encodeToString(Contact.serializer(), contact)
        return "${ContactNavigation.route}/$contactDetailRouteName/$encodedContact"
    }

    object ContactType: NavType<Contact>(isNullableAllowed = true) {
        override fun get(bundle: Bundle, key: String): Contact? {
            return bundle.getParcelable(key)
        }
        override fun parseValue(value: String): Contact {
            return Json.decodeFromString(value)
        }
        override fun put(bundle: Bundle, key: String, value: Contact) {
            bundle.putParcelable(key, value)
        }
    }
}
