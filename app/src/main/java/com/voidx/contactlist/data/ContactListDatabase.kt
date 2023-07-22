package com.voidx.contactlist.data

import android.content.Context
import com.voidx.contactlist.data.model.Contact
import java.io.BufferedReader
import java.io.InputStreamReader
import javax.inject.Inject

interface ContactListDatabase {

    val contacts: MutableList<Contact>

    fun initialize()

    class Impl @Inject constructor(
        private val context: Context
    ) : ContactListDatabase {

        override val contacts: MutableList<Contact> = mutableListOf()

        override fun initialize() {
            context.resources.assets.open("sample_contacts.csv").use { inputStream ->
                InputStreamReader(inputStream).use { inputReader ->
                    BufferedReader(inputReader).use { reader ->
                        var line: String?
                        var count = 0
                        while (reader.readLine().also { line = it } != null) {
                            // ignores the first loop, it is the header
                            if (count == 0) {
                                count++
                                continue
                            }

                            val row: List<String> = line!!.split(";")
                            contacts.add(buildContact(id = count, row))

                            count++
                        }
                    }
                }
            }
        }

        private fun buildContact(id: Int, row: List<String>): Contact = Contact(
            id = id,
            firstName = row.getOrNull(0).orEmpty(),
            lastName = row.getOrNull(1).orEmpty(),
            companyName = row.getOrNull(2).orEmpty(),
            address = row.getOrNull(3).orEmpty(),
            city = row.getOrNull(4).orEmpty(),
            county = row.getOrNull(5).orEmpty(),
            state = row.getOrNull(6).orEmpty(),
            zip = row.getOrNull(7).orEmpty(),
            phone = row.getOrNull(8).orEmpty(),
            phone1 = row.getOrNull(9).orEmpty(),
            email = row.getOrNull(10).orEmpty(),
        )
    }
}
