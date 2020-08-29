package com.gromyk.compose.playground.ui.config.components

import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.ConstraintLayout
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.gromyk.compose.playground.data.ItemData


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
            value.title,
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