package com.voidx.contactlist

import android.app.Application
import com.voidx.contactlist.data.ContactListDatabase
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class ContactListApplication: Application() {

    @Inject
    lateinit var database: ContactListDatabase

    override fun onCreate() {
        super.onCreate()
        database.initialize()
    }
}
