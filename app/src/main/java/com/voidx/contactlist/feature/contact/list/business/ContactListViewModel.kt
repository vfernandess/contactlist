package com.voidx.contactlist.feature.contact.list.business

import androidx.lifecycle.ViewModel
import com.voidx.contactlist.data.repository.ContactRepository
import com.voidx.contactlist.feature.contact.list.ui.ContactListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@HiltViewModel
class ContactListViewModel @Inject constructor(
    private val repository: ContactRepository
) : ViewModel() {

    private val mutableState = MutableStateFlow(ContactListState())

    val state: StateFlow<ContactListState>
        get() = mutableState

    suspend fun load() {
        repository
            .listAll()
            .flowOn(Dispatchers.IO)
            .collect {
                val newState = mutableState.value.copy(contacts = it)
                mutableState.tryEmit(newState)
            }
    }
}
