package com.daman.edman.screens.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.daman.edman.ui.theme.grayColor
import com.trend.camelx.ui.theme.large
import com.trend.camelx.ui.theme.medium
import com.trend.camelx.ui.theme.spacing

@Composable
fun UserInfoItem (
    modifier: Modifier = Modifier,
    name : String,
    phone : String,
    email : String,
    id : String
){
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(large),
        border = BorderStroke(width = 1.dp, color = grayColor)
    ) {

        Column(modifier = Modifier.padding(spacing)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                CheckedItem(
                    text = name
                )
                AppSpacer(width = medium)
                CheckedItem(
                    text = phone
                )
            }
            AppSpacer(height = large)

            Row(verticalAlignment = Alignment.CenterVertically) {
                CheckedItem(
                    text = email
                )
                AppSpacer(width = medium)
                CheckedItem(
                    text = id
                )
            }
        }

    }
}

@Preview
@Composable
fun UserInfoItemPreview() {
    UserInfoItem(
        name = "Name",
        phone = "Phone",
        email = "Email",
        id = "ID"
    )
}