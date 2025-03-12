package com.daman.edman.screens.components

import android.view.View
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.daman.edman.R
import com.trend.camelx.ui.theme.large
import com.trend.camelx.ui.theme.medium

@Composable
fun SettingsItem(icon : Int, text : String, modifier: Modifier = Modifier, onClick : () -> Unit) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(color = Color.White)
            .border(
                width = 1.dp,
                color = LightGray,
                shape = RoundedCornerShape(16.dp)
            )
            .clickable { onClick() }
            .padding(large),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = painterResource(id = icon),
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp)
            )

            Text(
                text = text,
                fontSize = 16.sp,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
        Image(
            painter = painterResource(id = R.drawable.ic_back_arrow_black),
            contentDescription = null,
            modifier = Modifier
                .size(20.dp)
                .rotate(if (LocalContext.current.resources.configuration.layoutDirection == View.LAYOUT_DIRECTION_LTR) 180f else 0f)
        )

    }

}

@Preview
@Composable
fun SettingsItemPreview() {
    SettingsItem(icon = R.drawable.ic_profile, text = "الملف الشخصي") {}
}