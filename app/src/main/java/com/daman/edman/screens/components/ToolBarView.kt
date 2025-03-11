package com.daman.edman.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.daman.edman.R
import com.trend.camelx.ui.theme.large

@Composable
fun ToolBarView(
    text: String
) {

    Box(modifier = Modifier
        .fillMaxWidth()
        .height(64.dp)
        .background(color = Color.White)) {

        HeaderText(text = text, fontSize = 16, modifier = Modifier.align(Alignment.Center))

        Icon(
            painter = painterResource(id = R.drawable.ic_back),
            contentDescription = null,
            modifier = Modifier
                .size(24.dp)
                .align(Alignment.CenterStart)
                .clickable {

                }
        )

        HorizontalDivider(modifier = Modifier.align(Alignment.BottomCenter).fillMaxWidth())
    }
}

@Preview
@Composable
fun ToolBarViewPreview() {
    ToolBarView(text = "Tool Bar View")
}