package com.daman.edman.screens.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.daman.edman.R
import com.daman.edman.ui.theme.borderColor
import com.daman.edman.ui.theme.grayColor
import com.trend.camelx.ui.theme.large
import com.trend.camelx.ui.theme.medium

@Composable
fun CheckedItem(
    modifier: Modifier = Modifier,
    text : String
) {

    Surface(
        modifier = modifier.height(34.dp),
        color = borderColor,
        shape = RoundedCornerShape(large)
    ) {

        Row(modifier = Modifier.padding(start = large, end = large),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            NormalText(
                text = text, fontSize = 14,
                modifier = Modifier
            )

            AppSpacer(width = medium)
            Image(
                painter = painterResource(id = R.drawable.ic_check),
                contentDescription = null,
                modifier = Modifier.size(18.dp)
            )
        }
    }

}

@Preview
@Composable
fun CheckedItemPreview() {
    CheckedItem(text = "Checked Item")
}