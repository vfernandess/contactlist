package com.voidx.contactlist.feature.contact.list.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.voidx.contactlist.data.model.Contact
import com.voidx.contactlist.feature.contact.list.business.ContactListViewModel
import com.voidx.contactlist.ui.theme.ContactListTheme

typealias OnContactSelected = (Contact) -> Unit

@Composable
fun ContactListScreen(
    viewModel: ContactListViewModel = hiltViewModel(),
    onContactSelected: OnContactSelected
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.load()
    }

    ContactListScreenContent(state, onContactSelected)
}

@Composable
private fun ContactListScreenContent(
    state: ContactListState,
    onContactSelected: OnContactSelected,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(
            items = state.contacts,
            key = { it.id }
        ) {
            BuildContactItem(it, onContactSelected)
        }
    }
}

@Composable
private fun BuildContactItem(
    contact: Contact,
    onContactSelected: OnContactSelected
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onContactSelected(contact)
            }
    ) {
        Text(
            modifier = Modifier.padding(16.dp),
            text = contact.name
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ContactListTheme {
        ContactListScreenContent(
            state = ContactListState(
                contacts = listOf(
                    Contact(
                        id = 0,
                        firstName = "John",
                        lastName = "Doe",
                        companyName = "John's company",
                        address = "4176 Sumner Street",
                        city = "Irvine",
                        county = "Irvine",
                        state = "CA",
                        zip = "92664",
                        phone = "310-594-5398",
                        phone1 = "949-294-9246",
                        email = "john@doe.com",
                    )
                )
            ),
            onContactSelected = {},
            modifier = Modifier.fillMaxSize()
        )
    }
}
