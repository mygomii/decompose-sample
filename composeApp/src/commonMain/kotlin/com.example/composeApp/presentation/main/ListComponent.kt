package com.example.composeApp.presentation.main

import com.arkivanov.decompose.value.Value
import com.example.composeApp.models.Post

interface ListComponent {
    val model: Value<List<Post>>

    fun onPostClicked(post: Post)

    fun onPostClicked()
}