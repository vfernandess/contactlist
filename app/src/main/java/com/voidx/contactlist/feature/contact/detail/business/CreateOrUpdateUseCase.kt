package com.voidx.contactlist.feature.contact.detail.business

import com.voidx.contactlist.data.model.Contact
import com.voidx.contactlist.data.repository.ContactRepository
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMap
import kotlinx.coroutines.flow.flatMapConcat
import javax.inject.Inject

interface CreateOrUpdateUseCase {

    operator fun invoke(contact: Contact): Flow<Boolean>

    class Impl @Inject constructor(
        private val repository: ContactRepository
    ) : CreateOrUpdateUseCase {
        @OptIn(FlowPreview::class)
        override operator fun invoke(contact: Contact): Flow<Boolean> {
            return repository
                .exists(contact.id)
                .flatMapConcat { exists ->
                    if (exists) {
                        repository.update(contact)
                    } else {
                        repository.create(contact)
                    }
                }
        }
    }
}
