package com.voidx.contactlist.feature.contact.list.business

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.voidx.contactlist.data.model.Contact
import com.voidx.contactlist.data.repository.ContactRepository
import com.voidx.contactlist.feature.contact.list.ui.ContactListSideState
import com.voidx.contactlist.feature.contact.list.ui.ContactListState
import com.voidx.contactlist.util.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactListViewModel
@Inject
constructor(
    private val dispatcherProvider: DispatcherProvider,
    private val repository: ContactRepository
) : ViewModel() {

    private val mutableState = MutableStateFlow(ContactListState())
    private val mutableSideState = MutableSharedFlow<ContactListSideState>()

    val state: StateFlow<ContactListState> =
        mutableState

    val sideState: SharedFlow<ContactListSideState> =
        mutableSideState

    suspend fun load() {
        repository
            .listAll()
            .flowOn(dispatcherProvider.io)
            .collect {
                val newState = mutableState.value.copy(contacts = it)
                mutableState.tryEmit(newState)
            }
    }

    fun createContact() {
        viewModelScope.launch(context = dispatcherProvider.main) {
            mutableSideState.emit(
                ContactListSideState.CreateContact(
                    Contact.empty().copy(id = state.value.contacts.size)
                )
            )
        }
    }
}
