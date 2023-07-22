package com.voidx.contactlist.feature.contact.detail.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.voidx.contactlist.R
import com.voidx.contactlist.data.model.Contact
import com.voidx.contactlist.feature.contact.detail.business.ContactDetailViewModel
import com.voidx.contactlist.ui.theme.ContactListTheme

@Composable
fun ContactDetailScreen(
    viewModel: ContactDetailViewModel = hiltViewModel(),
    onBackPressed: () -> Unit,
    onContactSaved: () -> Unit
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    if (state.saved) {
        onContactSaved()
    }

    Scaffold(
        topBar = { BuildTopAppBar(onBackPressed) },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = { Text(text = "Save") },
                icon = {
                    Icon(
                        painter = painterResource(id = R.drawable.pencil_outline),
                        contentDescription = null
                    )
                },
                onClick = { viewModel.save() }
            )
        },
        content = { padding ->
            ContactDetailScreenContent(
                modifier = Modifier.padding(padding),
                state = state
            )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BuildTopAppBar(
    onBackPressed: () -> Unit
) {
    TopAppBar(
        modifier = Modifier,
        navigationIcon = {
            IconButton(onClick = onBackPressed) {
                Icon(
                    painter = painterResource(id = R.drawable.arrow_left),
                    contentDescription = ""//stringResource(id = android.R.string.back)
                )
            }
        },
        title = {
            Text(text = "Contact details")
        }
    )
}

@Composable
fun ContactDetailScreenContent(
    modifier: Modifier = Modifier,
    state: ContactDetailState,
) {
    Column(
        modifier = modifier
            .padding(8.dp)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        BuildEditableText(
            label = "first name",
            initialText = state.contact.firstName,
            onValueChanged = { state.contact.firstName = it }
        )

        BuildEditableText(
            label = "last name",
            initialText = state.contact.lastName,
            onValueChanged = { state.contact.lastName = it }
        )

        BuildEditableText(
            label = "company name",
            initialText = state.contact.companyName,
            onValueChanged = { state.contact.companyName = it }
        )

        BuildEditableText(
            label = "address",
            initialText = state.contact.address,
            onValueChanged = { state.contact.address = it }
        )

        BuildEditableText(
            label = "city",
            initialText = state.contact.city,
            onValueChanged = { state.contact.city = it }
        )

        BuildEditableText(
            label = "county",
            initialText = state.contact.county,
            onValueChanged = { state.contact.county = it }
        )

        BuildEditableText(
            label = "state",
            initialText = state.contact.state,
            onValueChanged = { state.contact.state = it }
        )

        BuildEditableText(
            label = "zip",
            initialText = state.contact.zip,
            onValueChanged = { state.contact.zip = it }
        )

        BuildEditableText(
            label = "phone",
            initialText = state.contact.phone,
            onValueChanged = { state.contact.phone = it }
        )

        BuildEditableText(
            label = "phone1",
            initialText = state.contact.phone1,
            onValueChanged = { state.contact.phone1 = it }
        )

        BuildEditableText(
            label = "email",
            initialText = state.contact.email,
            onValueChanged = { state.contact.email = it }
        )

    }
}

@Composable
fun BuildEditableText(
    label: String,
    initialText: String,
    onValueChanged: (String) -> Unit
) {
    var text by remember { mutableStateOf(initialText) }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        value = text,
        onValueChange = {
            text = it
            onValueChanged(it)
        },
        label = { Text(label) }
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ContactListTheme {
        ContactDetailScreenContent(
            state = ContactDetailState(
                contact =
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
            ),
            modifier = Modifier.fillMaxSize()
        )
    }
}
