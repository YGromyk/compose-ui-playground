package com.gromyk.compose.playground

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.ui.tooling.preview.Preview
import com.gromyk.compose.playground.ui.config.ComposePlaygroundTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposePlaygroundTheme {
    }
}

interface Router {
    fun navigate(destination: Screen)
    fun back()
}

sealed class Screen {
    object Home : Screen()
    class Details(val newsId: Int?) : Screen()
}