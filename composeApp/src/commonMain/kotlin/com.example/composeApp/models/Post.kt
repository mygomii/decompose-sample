package com.example.composeApp.models

import kotlinx.serialization.Serializable

@Serializable
data class Post(
    val id: String,
    val author: String,
    val title: String,
    val description: String,
)
