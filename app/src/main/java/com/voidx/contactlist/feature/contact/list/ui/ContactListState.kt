package com.voidx.contactlist.feature.contact.list.ui

import com.voidx.contactlist.data.model.Contact

data class ContactListState(
    val isLoading: Boolean = false,
    val showEmptyState: Boolean = false,
    val showError: Boolean = false,
    val error: String? = null,
    val contacts: List<Contact> = emptyList(),
    val createContact: Boolean = false
)

sealed class ContactListSideState {

    data class CreateContact(
        val contact: Contact
    ) : ContactListSideState()
}
