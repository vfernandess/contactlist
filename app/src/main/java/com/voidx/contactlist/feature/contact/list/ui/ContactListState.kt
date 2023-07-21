package com.voidx.contactlist.feature.contact.list.ui

import com.voidx.contactlist.data.model.Contact

data class ContactListState(
    val isLoading: Boolean = true,
    val showEmptyState: Boolean = false,
    val showError: Boolean = false,
    val error: String? = null,
    val contacts: List<Contact> = emptyList(),
)
