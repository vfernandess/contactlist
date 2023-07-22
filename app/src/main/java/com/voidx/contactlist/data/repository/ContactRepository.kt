package com.voidx.contactlist.data.repository

import com.voidx.contactlist.data.model.Contact
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface ContactRepository {

    fun listAll(): Flow<List<Contact>>

    fun create(contact: Contact): Flow<Boolean>

    fun update(contact: Contact): Flow<Boolean>

    fun exists(id: Int): Flow<Boolean>

    class Impl @Inject constructor(
        private val localContactRepository: ContactRepository
    ) : ContactRepository {
        override fun listAll(): Flow<List<Contact>> =
            localContactRepository.listAll()

        override fun create(contact: Contact): Flow<Boolean> =
            localContactRepository.create(contact)

        override fun update(contact: Contact): Flow<Boolean> =
            localContactRepository.update(contact)

        override fun exists(id: Int): Flow<Boolean> =
            localContactRepository.exists(id)
    }
}
