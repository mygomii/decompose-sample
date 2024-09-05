package com.example.composeApp.presentation.root

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.StackAnimation
import com.arkivanov.essenty.backhandler.BackHandler
import com.example.composeApp.presentation.detail.DetailContent
import com.example.composeApp.presentation.main.ListContent
import com.example.composeApp.presentation.web.WebContent

@Composable
fun RootContent(
    component: RootComponent,
    modifier: Modifier = Modifier,
) {
    Children(
        stack = component.stack,
        modifier = modifier,
        animation = backAnimation(
            backHandler = component.backHandler,
            onBack = component::onBackClicked
        )
    ) {
        when (val child = it.instance) {
            is RootComponent.Child.Detail -> DetailContent(
                component = child.component,
                modifier = Modifier.fillMaxSize(),
            )

            is RootComponent.Child.List -> ListContent(
                component = child.component,
                modifier = Modifier.fillMaxSize(),
            )

            is RootComponent.Child.Web -> WebContent(
                component = child.component
            )
        }
    }
}

expect fun <C : Any, T : Any> backAnimation(
    backHandler: BackHandler,
    onBack: () -> Unit,
): StackAnimation<C, T>