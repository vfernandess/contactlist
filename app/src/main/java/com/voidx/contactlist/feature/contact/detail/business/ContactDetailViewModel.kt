package com.voidx.contactlist.feature.contact.detail.business

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.voidx.contactlist.data.model.Contact
import com.voidx.contactlist.feature.contact.detail.ContactDetailRouter
import com.voidx.contactlist.feature.contact.detail.ui.ContactDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val createOrUpdateUseCase: CreateOrUpdateUseCase
) : ViewModel() {

    private val mutableState =
        MutableStateFlow(
            ContactDetailState(
                savedStateHandle[ContactDetailRouter.contactArgName] ?: Contact.empty()
            )
        )

    val state: StateFlow<ContactDetailState>
        get() = mutableState

    fun save() {
        viewModelScope.launch(context = Dispatchers.IO) {
            createOrUpdateUseCase(state.value.contact)
                .collect {
                    val newState = state.value.copy(saved = it)
                    mutableState.tryEmit(newState)
                }
        }
    }
}
