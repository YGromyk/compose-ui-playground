package com.gromyk.compose.playground.ui.config.components

import androidx.compose.runtime.Composable
import com.gromyk.compose.playground.ui.config.ComposePlaygroundTheme

@Composable
fun ScreenWrapper(ui: @Composable () -> Unit) {
    ComposePlaygroundTheme {
        ui()
    }
}