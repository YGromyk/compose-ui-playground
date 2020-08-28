package com.gromyk.compose.playground

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.RowScope.gravity
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.ui.tooling.preview.Preview
import com.gromyk.compose.playground.ui.ComposePlaygroundTheme
import com.gromyk.compose.playground.ui.purple200
import com.gromyk.compose.playground.ui.purple700
import kotlinx.coroutines.selects.selectUnbiased

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposePlaygroundTheme {
                Surface(color = MaterialTheme.colors.background) {
                    HomeScreen()
                }
            }
        }
    }
}

@Composable
fun HomeScreen() {
    Scaffold(
        drawerContent = {
            Column {
                DrawerColumn("Column 1")
                DrawerColumn("Column 2")
                DrawerColumn("Column 3")
            }
        },
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(
                        onClick = { })
                    {
                        Icon(Icons.Filled.Menu)
                    }
                },
                title = {
                    Text(textAlign = TextAlign.Center, text = "Home")
                }
            )
        },
        bodyContent = {
            Content()
        }
    )
}

@Composable
private fun DrawerColumn(title: String) {
    Text(
        modifier = Modifier.fillMaxWidth()
            .gravity(align = Alignment.CenterVertically),
        text = title
    )
}


@Composable
fun Content() {
    Scaffold {
        ScrollableColumn(contentPadding = InnerPadding(4.dp)) {
            repeat(25) {
                Item("${it + 1}")
            }
        }
    }
}

@Composable
fun Item(value: String) {

    ConstraintLayout(
        modifier = Modifier
            .padding(top = 3.dp)
            .background(Color.DarkGray)
            .fillMaxWidth()
            .clickable(onClick = {})
    ) {
        val (textName, textDescription) = createRefs()

        Text("$value) Name", Modifier.constrainAs(textName) {
            top.linkTo(parent.top, 16.dp)
            start.linkTo(parent.start, 16.dp)
        })
        Text(
            "$value) Some random description",
            Modifier.constrainAs(textDescription) {
                start.linkTo(textName.start)
                top.linkTo(textName.bottom, 16.dp)
                bottom.linkTo(parent.bottom, 16.dp)
            })
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposePlaygroundTheme {
        HomeScreen()
    }
}