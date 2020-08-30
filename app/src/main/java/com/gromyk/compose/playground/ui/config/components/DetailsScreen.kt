package com.gromyk.compose.playground.ui.config.components

import androidx.compose.foundation.Icon
import androidx.compose.foundation.Text
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.text.style.TextAlign
import com.gromyk.compose.playground.Router
import com.gromyk.compose.playground.data.ItemData

@Composable
fun DetailsScreen(_data: ItemData, router: Router) {
    val data = remember { _data }
    ScreenWrapper {
        Scaffold(
            topBar = {
                TopAppBar(
                    navigationIcon = {
                        IconButton(onClick = { router.back() }) {
                            Icon(Icons.Filled.ArrowBack)
                        }
                    },
                    title = { Text(textAlign = TextAlign.Center, text = "Details") }
                )
            },
            bodyContent = { Item(data) {} }
        )
    }
}