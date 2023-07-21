package com.voidx.contactlist.feature.contact.detail.ui

import com.voidx.contactlist.data.model.Contact

data class ContactDetailState(
    val contact: Contact,
    val saved: Boolean = false
)
