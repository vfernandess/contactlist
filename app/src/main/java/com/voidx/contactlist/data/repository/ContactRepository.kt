package com.voidx.contactlist.data.repository

import com.voidx.contactlist.data.model.Contact
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface ContactRepository {

    fun listAll(): Flow<List<Contact>>

    fun createOrUpdate(contact: Contact): Flow<Boolean>

    class Impl @Inject constructor(
        private val localContactRepository: ContactRepository
    ) : ContactRepository {
        override fun listAll(): Flow<List<Contact>> =
            localContactRepository.listAll()

        override fun createOrUpdate(contact: Contact): Flow<Boolean> =
            localContactRepository.createOrUpdate(contact)
    }
}
