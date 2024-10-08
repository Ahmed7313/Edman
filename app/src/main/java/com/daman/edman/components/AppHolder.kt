package com.daman.edman.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.trend.camelx.ui.theme.large
import com.trend.camelx.ui.theme.spacing

@Composable
fun AppHolder(modifier: Modifier = Modifier,
              view : @Composable ()-> Unit) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(start = spacing, end = spacing, top = large, bottom = large)
    ) {
        view()
    }
}