package com.voidx.contactlist.data.repository.local

import app.cash.turbine.test
import com.voidx.contactlist.data.ContactListDatabase
import com.voidx.contactlist.data.faker
import com.voidx.contactlist.data.mockedContacts
import com.voidx.contactlist.data.model.Contact
import com.voidx.contactlist.util.UnitTest
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class LocalContactRepositoryTest : UnitTest() {

    lateinit var database: ContactListDatabase

    override fun setUp() {
        super.setUp()
        database = mock()
    }

    @Test
    fun `should successfully list all contacts`() = runTest {
        whenever(database.contacts).doReturn(mockedContacts)

        val repository = LocalContactRepository(database)
        repository
            .listAll()
            .test {

                val result = awaitItem()

                assertEquals(3, result.size)

                awaitComplete()
            }
    }

    @Test
    fun `should successfully add a new contact`() = runTest {
        whenever(database.contacts).doReturn(mutableListOf())

        val repository = LocalContactRepository(database)
        repository
            .create(Contact.empty())
            .test {
                awaitItem()

                assertEquals(1, database.contacts.size)

                awaitComplete()
            }
    }

    @Test
    fun `should successfully update contact`() = runTest {
        val mock = mockedContacts
        whenever(database.contacts ).doReturn(mock)

        val mockedFirstName = faker.name.firstName()
        val updatedContact = mock.first().copy(firstName = mockedFirstName)

        val repository = LocalContactRepository(database)
        repository
            .update(updatedContact)
            .test {
                awaitItem()

                assertEquals(mockedFirstName, database.contacts.first().firstName)

                awaitComplete()
            }
    }
}
