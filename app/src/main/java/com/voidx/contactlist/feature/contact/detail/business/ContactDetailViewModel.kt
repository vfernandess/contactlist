package com.voidx.contactlist.feature.contact.detail.business

import androidx.lifecycle.ViewModel
import com.voidx.contactlist.data.model.Contact
import com.voidx.contactlist.feature.contact.detail.ui.ContactDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@HiltViewModel
class ContactDetailViewModel(
    contact: Contact
) : ViewModel() {

    private val mutableState = MutableStateFlow(ContactDetailState(contact = contact))

    val state: StateFlow<ContactDetailState>
        get() = mutableState

    fun save() {
        val newState = state.value.copy(saved = true)
        mutableState.tryEmit(newState)
    }
}
