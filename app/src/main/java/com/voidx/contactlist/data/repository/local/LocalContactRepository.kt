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

    override fun createOrUpdate(contact: Contact): Flow<Boolean> = flow {
        emit(database.contacts.add(contact))
    }
}
