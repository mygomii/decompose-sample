package com.example.composeApp.presentation.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Call
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.subscribeAsState

@Composable
fun ListContent(
    component: ListComponent,
    modifier: Modifier = Modifier,
) {
    val state by component.model.subscribeAsState()

    Scaffold(
        modifier = modifier,
        topBar = { TopAppBar(title = { Text("List") }) }
    ) { paddingValues ->

        IconButton(
            modifier = Modifier.padding(20.dp)
                .background(Color.Gray),
            onClick = component::onPostClicked
        ) {
            Icon(Icons.Outlined.Call, contentDescription = "Back")
        }
    }
}