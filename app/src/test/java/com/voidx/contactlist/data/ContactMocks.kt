package com.voidx.contactlist.data

import com.voidx.contactlist.data.model.Contact
import io.bloco.faker.Faker
import kotlinx.coroutines.flow.flow

val faker by lazy { Faker() }

val flowMockedContacts = flow { emit(mockedContacts) }

val flowMockSingleContact = flow { emit(mockedContacts.first()) }

val mockedContacts by lazy {
    val result = mutableListOf<Contact>()
    for (index in 1 until 4) {
        result.add(
            Contact(
                id = index,
                firstName = faker.name.firstName(),
                lastName = faker.name.lastName(),
                companyName = faker.company.name(),
                address = faker.address.streetAddress(),
                city = faker.address.city(),
                county = faker.address.city(),
                state = faker.address.state(),
                zip = faker.address.postcode(),
                phone = faker.phoneNumber.phoneNumber(),
                phone1 = faker.phoneNumber.cellPhone(),
                email = faker.internet.email(),
            )
        )
    }

    return@lazy result
}


