package com.example.composeApp

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

interface DynamicFeatureContent<T : Any> {

    @Composable
    operator fun invoke(component: T, modifier: Modifier)
}
