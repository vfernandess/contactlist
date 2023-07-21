package com.voidx.contactlist.data

import android.content.Context
import com.voidx.contactlist.data.repository.ContactRepository
import com.voidx.contactlist.data.repository.local.LocalContactRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun providesContactRepository(database: ContactListDatabase): ContactRepository {
        return ContactRepository.Impl(
            localContactRepository = LocalContactRepository(database)
        )
    }

    @Singleton
    @Provides
    fun providesContactListDatabase(@ApplicationContext context: Context): ContactListDatabase {
        return ContactListDatabase.Impl(context)
    }
}
