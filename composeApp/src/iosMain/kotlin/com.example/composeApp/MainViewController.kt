package com.example.composeApp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.ComposeUIViewController
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.stack.animation.predictiveback.PredictiveBackGestureOverlay
import com.arkivanov.essenty.backhandler.BackDispatcher
import com.arkivanov.essenty.lifecycle.ApplicationLifecycle
import com.example.composeApp.presentation.root.DefaultRootComponent

/*fun MainViewController() = ComposeUIViewController {
    val rootComponent = remember {
        DefaultRootComponent(DefaultComponentContext(lifecycle = ApplicationLifecycle()))
    }

    App(rootComponent = rootComponent)
}*/


@OptIn(ExperimentalDecomposeApi::class)
fun MainViewController(backDispatcher: BackDispatcher) = ComposeUIViewController {
    PredictiveBackGestureOverlay(
        backDispatcher = backDispatcher,
        backIcon = null,
        modifier = Modifier.fillMaxSize(),
        onClose = { println("ON CLOSE CALLED ===") }
    ) {
        val root = remember {
            DefaultRootComponent(
                componentContext = DefaultComponentContext(lifecycle = ApplicationLifecycle(), backHandler = backDispatcher),
            )
        }

        App(root)
    }

}