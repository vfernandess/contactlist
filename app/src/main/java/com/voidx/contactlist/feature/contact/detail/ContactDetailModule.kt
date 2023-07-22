package com.voidx.contactlist.feature.contact.detail

import com.voidx.contactlist.feature.contact.detail.business.CreateOrUpdateUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class ContactDetailModule {

    @Binds
    abstract fun bindsCreateOrUpdateUseCase(useCase: CreateOrUpdateUseCase.Impl): CreateOrUpdateUseCase
}
