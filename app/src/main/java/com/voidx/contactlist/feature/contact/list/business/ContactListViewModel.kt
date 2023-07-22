package com.voidx.contactlist.feature.contact.list.business

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.voidx.contactlist.data.model.Contact
import com.voidx.contactlist.data.repository.ContactRepository
import com.voidx.contactlist.feature.contact.list.ui.ContactListSideState
import com.voidx.contactlist.feature.contact.list.ui.ContactListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.shareIn
import javax.inject.Inject

@HiltViewModel
class ContactListViewModel @Inject constructor(
    private val repository: ContactRepository
) : ViewModel() {

    private val mutableState = MutableStateFlow(ContactListState())
    private val mutableSideState = Channel<ContactListSideState>()

    val state: StateFlow<ContactListState>
        get() = mutableState

    val sideState = mutableSideState.receiveAsFlow().shareIn(viewModelScope, SharingStarted.Lazily)

    suspend fun load() {
        repository
            .listAll()
            .flowOn(Dispatchers.IO)
            .collect {
                val newState = mutableState.value.copy(contacts = it)
                mutableState.tryEmit(newState)
            }
    }

    fun createContact() {
        mutableSideState.trySend(
            ContactListSideState.CreateContact(
                Contact.empty().copy(id = state.value.contacts.size)
            )
        )
    }
}
