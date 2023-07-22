package com.voidx.contactlist.util

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import javax.inject.Singleton

interface DispatcherProvider {
    val main: CoroutineDispatcher
    val io: CoroutineDispatcher
    val default: CoroutineDispatcher

    class Impl @Inject constructor() : DispatcherProvider {
        override val main: CoroutineDispatcher = Dispatchers.Main
        override val io: CoroutineDispatcher = Dispatchers.IO
        override val default: CoroutineDispatcher = Dispatchers.Default
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class DispatcherModule {

    @Binds
    @Singleton
    abstract fun bindsDispatcherProvider(provider: DispatcherProvider.Impl): DispatcherProvider
}
