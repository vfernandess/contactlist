package com.voidx.contactlist.effect

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext


@Composable
fun LifecycleEffect(
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    coroutineContext: CoroutineContext = Dispatchers.Main,
    onCreate: suspend CoroutineScope.() -> Unit = { },
    onStart: suspend CoroutineScope.() -> Unit = { },
) {
    val currentOnStart by rememberUpdatedState(onStart)
    val currentOnCreate by rememberUpdatedState(onCreate)

    val scope = rememberCoroutineScope { coroutineContext }

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_CREATE) {
                scope.launch(block = currentOnStart)
            }

            if (event == Lifecycle.Event.ON_START) {
                scope.launch(block = currentOnCreate)
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)

        // When the effect leaves the Composition, remove the observer
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
}
