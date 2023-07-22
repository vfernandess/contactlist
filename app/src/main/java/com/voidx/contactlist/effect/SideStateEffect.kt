package com.voidx.contactlist.effect

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.flow.Flow

@Composable
fun <SideState> SideStateEffect(effect: Flow<SideState>, block: (SideState) -> Unit) {
    LaunchedEffect(Unit) {
        effect.collect(block)
    }
}
