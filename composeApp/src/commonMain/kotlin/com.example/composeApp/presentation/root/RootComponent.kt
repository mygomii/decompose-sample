package com.example.composeApp.presentation.root

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.backhandler.BackHandlerOwner
import com.example.composeApp.presentation.detail.DetailComponent
import com.example.composeApp.presentation.main.ListComponent
import com.example.composeApp.presentation.web.WebComponent

interface RootComponent : BackHandlerOwner {
    val stack: Value<ChildStack<*, Child>>

    fun onBackClicked()
    fun onBackClicked(toIndex: Int)

    sealed interface Child {
        class List(val component: ListComponent) : Child
        class Detail(val component: DetailComponent) : Child
        class Web(val component: WebComponent): Child
    }
}
