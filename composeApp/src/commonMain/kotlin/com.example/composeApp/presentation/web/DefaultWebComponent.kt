package com.example.composeApp.presentation.web

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.backhandler.BackHandlerOwner

class DefaultWebComponent(
    componentContext: ComponentContext,
    private val postClicked: () -> Unit,
) : WebComponent, ComponentContext by componentContext, BackHandlerOwner {
    override fun onBackPressed() = postClicked()
}
