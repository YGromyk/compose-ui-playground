package com.gromyk.compose.playground

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.ui.tooling.preview.Preview
import com.gromyk.compose.playground.ui.config.ComposePlaygroundTheme

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        navController = findNavController(R.id.nav_host_fragment)

    }

    private val router = object : Router {
        override fun navigate(destination: Screen) {
            when (destination) {
                is Screen.Home -> {
                    navController.navigate(R.id.homeDest)
                }
                is Screen.Details -> {

                }
            }
        }

        override fun back() {
            navController.popBackStack()
        }

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