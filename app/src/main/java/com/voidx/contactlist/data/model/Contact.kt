package com.voidx.contactlist.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Contact(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val companyName: String,
    val address: String,
    val city: String,
    val county: String,
    val state: String,
    val zip: String,
    val phone: String,
    val phone1: String,
    val email: String,
) : Parcelable {

    val name: String
        get() = "$firstName $lastName"

    override fun hashCode(): Int {
        return id
    }
}
