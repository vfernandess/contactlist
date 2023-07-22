package com.voidx.contactlist.feature.contact.detail.business

import com.voidx.contactlist.data.model.Contact
import com.voidx.contactlist.data.repository.ContactRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface CreateOrUpdateUseCase {

    operator fun invoke(contact: Contact): Flow<Boolean>

    class Impl @Inject constructor(
        private val repository: ContactRepository
    ): CreateOrUpdateUseCase {
        override operator fun invoke(contact: Contact): Flow<Boolean> {
            return if (repository.getById(contact.id) == null) {
                repository.create(contact)
            } else {
                repository.update(contact)
            }
        }
    }
}
