package com.example.composeApp.presentation.detail

import com.arkivanov.decompose.value.Value
import com.example.composeApp.models.Post

interface DetailComponent {
    val model: Value<Post>

    fun onBackPressed()
}