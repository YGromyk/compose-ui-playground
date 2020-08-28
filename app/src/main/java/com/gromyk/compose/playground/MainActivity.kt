package com.gromyk.compose.playground

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.RowScope.gravity
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Providers
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.github.zsoltk.compose.backpress.AmbientBackPressHandler
import com.github.zsoltk.compose.backpress.BackPressHandler
import com.github.zsoltk.compose.router.Router
import com.gromyk.compose.playground.ui.ComposePlaygroundTheme

class MainActivity : AppCompatActivity() {
    private val backPressHandler = BackPressHandler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposePlaygroundTheme {
                Surface(color = MaterialTheme.colors.background) {
                    Providers(
                        AmbientBackPressHandler provides backPressHandler
                    ) {
                        HomeScreen(Screen.Home)
                    }
                }
            }
        }
    }

    override fun onBackPressed() {
        if (!backPressHandler.handle()) {
            super.onBackPressed()
        }
    }
}

@Composable
fun HomeScreen(defaultRouting: Screen) {
    Router(defaultRouting) { backStack ->
        val router = object : Router {
            override fun back() {
                backStack.pop()
            }
        }

        when (val routing = backStack.last()) {
            is Screen.Home -> HomeScreen {
                backStack.push(Screen.Details(it))
            }
            is Screen.Details -> DetailsScreen(router, routing.item)
        }
    }
}

@Composable
private fun HomeScreen(onOpenDetails: (ItemData) -> Unit) {
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

fun DrawerState.reverse() {
    if (isClosed) {
        open {}
    } else {
        close()
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
                    "${it + 1}) A name",
                    "${it + 1}) Some random description",
                ),
                onOpenDetails
            )
        }
    }
}

@Composable
fun Item(value: ItemData, openDetails: (ItemData) -> Unit) {
    ConstraintLayout(
        modifier = Modifier
            .padding(top = 3.dp)
            .background(Color.DarkGray)
            .fillMaxWidth()
            .clickable(onClick = { openDetails(value) })
    ) {
        val (textName, textDescription) = createRefs()

        Text(
            value.name,
            Modifier.constrainAs(textName) {
                top.linkTo(parent.top, 16.dp)
                start.linkTo(parent.start, 16.dp)
            }
        )
        Text(
            value.description,
            Modifier.constrainAs(textDescription) {
                start.linkTo(textName.start)
                top.linkTo(textName.bottom, 16.dp)
                bottom.linkTo(parent.bottom, 16.dp)
            }
        )
    }
}

@Composable
private fun DetailsScreen(router: Router, _data: ItemData) {
    val data = remember { _data }
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

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposePlaygroundTheme {
    }
}


data class ItemData(val name: String, val description: String)

sealed class Screen {
    object Home : Screen()
    class Details(val item: ItemData) : Screen()
}

interface Router {
    fun back()
}