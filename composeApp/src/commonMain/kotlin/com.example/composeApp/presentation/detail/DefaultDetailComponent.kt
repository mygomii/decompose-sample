package com.example.composeApp.presentation.detail

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.example.composeApp.models.Post

class DefaultDetailComponent(
    componentContext: ComponentContext,
    post: Post,
    private val onFinished: () -> Unit,
) : DetailComponent, ComponentContext by componentContext {

    override val model: Value<Post> = MutableValue(post)

    override fun onBackPressed() = onFinished()
}
