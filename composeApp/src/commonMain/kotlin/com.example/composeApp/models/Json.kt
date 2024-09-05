package com.example.composeApp.models

import kotlinx.serialization.json.Json

internal val json =
    Json {
        allowStructuredMapKeys = true
    }
