package com.voidx.contactlist.feature.contact.detail.business

import app.cash.turbine.test
import com.voidx.contactlist.data.model.Contact
import com.voidx.contactlist.data.repository.ContactRepository
import com.voidx.contactlist.util.UnitTest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class CreateOrUpdateUseCaseTest: UnitTest() {

    lateinit var repository: ContactRepository

    override fun setUp() {
        super.setUp()
        repository = mock()
    }

    @Test
    fun `should create an user`() = runTest {
        whenever(repository.exists(any())).doReturn(flow { emit(false) })

        val contact = Contact.empty()

        val useCase = CreateOrUpdateUseCase.Impl(repository)
        useCase
            .invoke(contact)
            .test {
                verify(repository).create(contact)

                cancelAndConsumeRemainingEvents()
            }
    }

    @Test
    fun `should update an user`() = runTest {
        whenever(repository.exists(any())).doReturn(flow { emit(true) })

        val contact = Contact.empty()

        val useCase = CreateOrUpdateUseCase.Impl(repository)
        useCase
            .invoke(contact)
            .test {
                verify(repository).update(contact)

                cancelAndConsumeRemainingEvents()
            }
    }
}
