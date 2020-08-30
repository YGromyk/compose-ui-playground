package com.gromyk.compose.playground.ui.config.components

import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.InnerPadding
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.gromyk.compose.playground.data.ItemData

@Composable
fun HomeScreen(onOpenDetails: (ItemData) -> Unit) {
    ScreenWrapper {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(textAlign = TextAlign.Center, text = "Home") }
                )
            },
            bodyContent = { Content(onOpenDetails) }
        )
    }
}

@Composable
fun Content(onOpenDetails: (ItemData) -> Unit) {
    ScrollableColumn(contentPadding = InnerPadding(4.dp)) {
        repeat(25) {
            Item(
                ItemData(
                    it,
                    "${it}) A name",
                    "${it}) Some random description",
                ),
                onOpenDetails
            )
        }
    }
}