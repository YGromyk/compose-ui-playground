package com.gromyk.compose.playground.ui.config.components

import androidx.compose.foundation.Icon
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.InnerPadding
import androidx.compose.foundation.layout.RowScope.gravity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.gromyk.compose.playground.data.ItemData

@Composable
fun HomeScreen(onOpenDetails: (ItemData) -> Unit) {
    ScreenWrapper {
        Scaffold(
            drawerContent = {
                val drawerState = rememberDrawerState(DrawerValue.Closed)
                ModalDrawerLayout(
                    drawerState = drawerState,
                    bodyContent = {},
                    drawerContent = {
                        Column(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            DrawerColumn("Column 1")
                            DrawerColumn("Column 2")
                            DrawerColumn("Column 3")
                        }
                    }
                )
            },
            topBar = {
                TopAppBar(
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                // todo: open/close the drawer
                            }
                        ) {
                            Icon(Icons.Filled.Menu)
                        }
                    },
                    title = { Text(textAlign = TextAlign.Center, text = "Home") }
                )
            },
            bodyContent = { Content(onOpenDetails) }
        )
    }
}

@Composable
private fun DrawerColumn(title: String) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .gravity(align = Alignment.CenterVertically),
        text = title
    )
}


@Composable
fun Content(onOpenDetails: (ItemData) -> Unit) {
    ScrollableColumn(contentPadding = InnerPadding(4.dp)) {
        repeat(25) {
            Item(
                ItemData(
                    it,
                    "${it + 1}) A name",
                    "${it + 1}) Some random description",
                ),
                onOpenDetails
            )
        }
    }
}