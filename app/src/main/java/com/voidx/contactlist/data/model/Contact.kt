package com.voidx.contactlist.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Contact(
    val id: Int,
    var firstName: String,
    var lastName: String,
    var companyName: String,
    var address: String,
    var city: String,
    var county: String,
    var state: String,
    var zip: String,
    var phone: String,
    var phone1: String,
    var email: String,
) : Parcelable {

    val name: String
        get() = "$firstName $lastName"

    companion object {

        fun empty(): Contact {
            return Contact(
                id = -1,
                firstName = "",
                lastName = "",
                companyName = "",
                address = "",
                city = "",
                county = "",
                state = "",
                zip = "",
                phone = "",
                phone1 = "",
                email = "",
            )
        }
    }
}
