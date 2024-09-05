package com.example.composeApp.presentation.main

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.example.composeApp.models.Post

class DefaultListComponent(
    componentContext: ComponentContext,
    private val postClicked: (Post) -> Unit,
    private val postClicked1: () -> Unit
) : ListComponent, ComponentContext by componentContext {
    override val model: Value<List<Post>> = MutableValue(
        (0..16).map {
            Post(
                id = it.toString(),
                title = "Title-#$it",
                description = "Description-#$it",
                author = "Author-#$it",
            )
        }
    )

    override fun onPostClicked(post: Post) = postClicked(post)
    override fun onPostClicked() {
        postClicked1()
    }
}
