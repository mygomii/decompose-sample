package com.example.composeApp.root

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.ComposeUIViewController
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.extensions.compose.stack.animation.predictiveback.PredictiveBackGestureIcon
import com.arkivanov.decompose.extensions.compose.stack.animation.predictiveback.PredictiveBackGestureOverlay
import com.arkivanov.essenty.backhandler.BackDispatcher
import com.example.composeApp.App
import com.example.composeApp.presentation.root.RootComponent
import platform.UIKit.UIViewController

@OptIn(ExperimentalDecomposeApi::class)
fun rootViewController(root: RootComponent, backDispatcher: BackDispatcher): UIViewController =
    ComposeUIViewController {
        PredictiveBackGestureOverlay(
            backDispatcher = backDispatcher,
            backIcon = { progress, _ ->
                PredictiveBackGestureIcon(
                    imageVector = Icons.Default.Close,
                    progress = progress,
                )
            },
            modifier = Modifier.fillMaxSize(),
        ) {
            App(rootComponent = root)
//            RootContent(component = root, modifier = Modifier.fillMaxSize())
        }
    }