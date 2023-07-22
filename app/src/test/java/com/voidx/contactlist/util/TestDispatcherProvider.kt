package com.voidx.contactlist.util

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher

@OptIn(ExperimentalCoroutinesApi::class)
class TestDispatcherProvider(
    override val main: CoroutineDispatcher = UnconfinedTestDispatcher(),
    override val io: CoroutineDispatcher = UnconfinedTestDispatcher(),
    override val default: CoroutineDispatcher = UnconfinedTestDispatcher(),
) : DispatcherProvider
