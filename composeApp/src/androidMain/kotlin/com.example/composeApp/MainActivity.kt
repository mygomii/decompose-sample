package com.example.composeApp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.arkivanov.decompose.defaultComponentContext
import com.example.composeApp.presentation.root.DefaultRootComponent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        val rootComponent = DefaultRootComponent( defaultComponentContext())
        val rootComponent = DefaultRootComponent( defaultComponentContext())

        setContent {
            App(rootComponent = rootComponent)
        }
    }
}

//@Preview
//@Composable
//fun AppAndroidPreview() {
//    App()
//}