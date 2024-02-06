package com.daman.edman.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun AppHolder(modifier: Modifier = Modifier,
              view : @Composable ()-> Unit) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        view()
    }
}