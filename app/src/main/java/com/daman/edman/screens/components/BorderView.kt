package com.daman.edman.screens.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.daman.edman.ui.theme.borderColor
import com.trend.camelx.ui.theme.large
import com.trend.camelx.ui.theme.spacing

@Composable
fun BorderView(
    modifier: Modifier = Modifier,
    shape: RoundedCornerShape = RoundedCornerShape(large),
    content: @Composable () -> Unit
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = shape,
        border = BorderStroke(1.dp, borderColor)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = spacing, bottom = spacing, start = large, end = large)
        ) {
            content()
        }
    }
}