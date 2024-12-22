package com.daman.edman.screens.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.daman.edman.R

@Composable
 fun AppToolBar(
    modifier: Modifier = Modifier,
    onClick : () -> Unit
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(64.dp)
            .background(
                color = Color.White
            )
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Image(
            painter = painterResource(id = R.drawable.ic_tool_bar_logo),
            contentDescription = "Menu",
            modifier = Modifier
                .width(100.dp)
                .height(28.dp)
        )

        Image(
            painter = painterResource(id = R.drawable.ic_notification),
            contentDescription = "Menu",
            modifier = Modifier
                .width(28.dp)
                .height(28.dp)
                .clickable { onClick() }
        )
    }
}