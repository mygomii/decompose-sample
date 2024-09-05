package com.example.composeApp.presentation.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.popTo
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.router.stack.pushNew
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.backhandler.BackHandlerOwner
import com.example.composeApp.models.Post
import com.example.composeApp.presentation.detail.DefaultDetailComponent
import com.example.composeApp.presentation.main.DefaultListComponent
import com.example.composeApp.presentation.web.DefaultWebComponent
import com.example.composeApp.presentation.web.WebComponent
import kotlinx.serialization.Serializable

class DefaultRootComponent(
    componentContext: ComponentContext,
) : RootComponent, ComponentContext by componentContext, BackHandlerOwner {

    private val nav = StackNavigation<Config>()

    override val stack: Value<ChildStack<*, RootComponent.Child>> = childStack(
        source = nav,
        serializer = Config.serializer(),
        initialConfiguration = Config.List,
        handleBackButton = true,
        childFactory = ::child,
    )

    override fun onBackClicked() {
        nav.pop()
    }

    override fun onBackClicked(toIndex: Int) {
        nav.popTo(index = toIndex)
    }

    private fun child(
        config: Config,
        componentContext: ComponentContext,
    ): RootComponent.Child = when (config) {
        Config.List -> RootComponent.Child.List(
            DefaultListComponent(
                componentContext = componentContext,
                postClicked = { post ->
                    nav.pushNew(Config.Web)
//                    nav.pushNew(Config.Detail(post))
                },
                postClicked1 = {
                    nav.pushNew(Config.Web)
                }
            )
        )

        is Config.Detail -> RootComponent.Child.Detail(
            DefaultDetailComponent(
                componentContext = componentContext,
                post = config.post,
                onFinished = { nav.pop() },
            )
        )

        is Config.Web -> RootComponent.Child.Web(
            DefaultWebComponent(
                componentContext = componentContext,
                postClicked = { nav.pop() }
            )

        )
    }


    @Serializable
    private sealed interface Config {
        @Serializable
        data object List : Config

        @Serializable
        data class Detail(val post: Post) : Config

        @Serializable
        data object Web : Config
    }
}