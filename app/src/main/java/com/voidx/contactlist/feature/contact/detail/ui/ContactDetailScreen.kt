package com.voidx.contactlist.feature.contact.detail.ui

import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.voidx.contactlist.data.model.Contact
import com.voidx.contactlist.feature.contact.detail.business.ContactDetailViewModel

@Composable
fun ContactDetailScreen(
    contact: Contact,
    viewModel: ContactDetailViewModel = hiltViewModel()
) {
    TextButton(onClick = { viewModel.save() }) {
        Text(text = "save")
    }
}
