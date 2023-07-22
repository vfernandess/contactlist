package com.voidx.contactlist.feature.contact.list.business

import app.cash.turbine.test
import com.voidx.contactlist.data.flowMockedContacts
import com.voidx.contactlist.data.repository.ContactRepository
import com.voidx.contactlist.feature.contact.list.ui.ContactListSideState
import com.voidx.contactlist.util.DispatcherProvider
import com.voidx.contactlist.util.TestDispatcherProvider
import com.voidx.contactlist.util.UnitTest
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class ContactListViewModelTest : UnitTest() {

    private lateinit var repository: ContactRepository

    lateinit var dispatcherProvider: DispatcherProvider

    override fun setUp() {
        super.setUp()
        repository = mock()
        dispatcherProvider = TestDispatcherProvider()
    }

    @Test
    fun `should change state after loading all contacts`() = runTest {
        whenever(repository.listAll()).doReturn(flowMockedContacts)

        val contactListViewModel = ContactListViewModel(dispatcherProvider, repository)

        val job = launch {
            contactListViewModel.state.test {
                val newState = awaitItem()

                assertEquals(3, newState.contacts.size)

                cancelAndConsumeRemainingEvents()
            }
        }

        contactListViewModel.load()
        job.join()
        job.cancel()
    }

    @Test
    fun `should raise CreateContactSideState when createContact is invoked`() = runTest {
        val contactListViewModel = ContactListViewModel(dispatcherProvider, repository)

        contactListViewModel.sideState.test {
            contactListViewModel.createContact()

            val result = awaitItem()

            assertTrue(result is ContactListSideState.CreateContact)

            cancelAndConsumeRemainingEvents()
        }
    }
}
