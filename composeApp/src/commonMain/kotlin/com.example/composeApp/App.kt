package com.example.composeApp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.composeApp.presentation.root.RootComponent
import com.example.composeApp.presentation.root.RootContent
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App(rootComponent: RootComponent) {
    MaterialTheme {
        RootContent(
            component = rootComponent,
            modifier = Modifier.fillMaxSize(),
        )
    }
}