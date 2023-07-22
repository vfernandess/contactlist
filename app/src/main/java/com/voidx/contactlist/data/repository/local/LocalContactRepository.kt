package com.voidx.contactlist.data.repository.local

import com.voidx.contactlist.data.ContactListDatabase
import com.voidx.contactlist.data.model.Contact
import com.voidx.contactlist.data.repository.ContactRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LocalContactRepository @Inject constructor(
    private val database: ContactListDatabase
): ContactRepository {
    override fun listAll(): Flow<List<Contact>> = flow {
        emit(database.contacts)
    }

    override fun create(contact: Contact): Flow<Boolean> = flow {
        emit(database.contacts.add(contact))
    }

    override fun update(contact: Contact): Flow<Boolean> = flow {
        database.contacts[contact.id - 1] = contact
        emit(true)
    }

    override fun getById(id: Int): Flow<Contact?> = flow {
        emit(database.contacts.getOrNull(id - 1))
    }
}
