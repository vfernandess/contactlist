package com.voidx.contactlist.feature.contact.list.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.voidx.contactlist.R
import com.voidx.contactlist.data.model.Contact
import com.voidx.contactlist.effect.LifecycleEffect
import com.voidx.contactlist.effect.OnSideStateEffect
import com.voidx.contactlist.feature.contact.list.business.ContactListViewModel
import com.voidx.contactlist.ui.theme.ContactListTheme

typealias OnContactSelected = (Contact) -> Unit

@Composable
fun ContactListScreen(
    viewModel: ContactListViewModel = hiltViewModel(),
    onContactSelected: OnContactSelected,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LifecycleEffect(
        onCreate = {
            viewModel.load()
        }
    )

    OnSideStateEffect(viewModel.sideState) {
        if (it is ContactListSideState.CreateContact) {
            onContactSelected(it.contact)
        }
    }

    Scaffold(
        topBar = { BuildTopAppBar() },
        floatingActionButton = {
            SmallFloatingActionButton(
                onClick = {
                    viewModel.createContact()
                },
                content = {
                    Icon(
                        painter = painterResource(id = R.drawable.plus),
                        contentDescription = null
                    )
                }
            )
        },
        content = { padding ->
            ContactListScreenContent(
                state,
                onContactSelected,
                Modifier.padding(padding)
            )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BuildTopAppBar() {
    TopAppBar(
        modifier = Modifier,
        title = {
            Text(text = "Contact list")
        }
    )
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
        itemsIndexed(
            items = state.contacts,
            key = { _, item -> item.id },
            itemContent = { index, item ->
                BuildContactItem(index, state.contacts.lastIndex, item, onContactSelected)
            }
        )
    }
}

@Composable
private fun BuildContactItem(
    index: Int,
    lastIndex: Int,
    contact: Contact,
    onContactSelected: OnContactSelected
) {
    Column(
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

        if (index < lastIndex) {
            Divider(Modifier.padding(horizontal = 8.dp))
        }
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
