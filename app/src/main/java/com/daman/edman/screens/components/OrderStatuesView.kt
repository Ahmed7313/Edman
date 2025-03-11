package com.daman.edman.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.daman.edman.R
import com.daman.edman.ui.theme.SkyColorBlue
import com.daman.edman.ui.theme.borderColor
import com.daman.edman.ui.theme.lightBlue
import com.daman.edman.ui.theme.lightGrayColor
import com.daman.edman.ui.theme.yelloColor
import com.trend.camelx.ui.theme.large
import com.trend.camelx.ui.theme.medium

@Composable
fun OrderStatuesView(
    modifier: Modifier = Modifier,
    statues: String
) {

    Row(
        modifier = modifier
            .background(color = lightGrayColor, shape = RoundedCornerShape(large))
            .padding(medium),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            painter = painterResource(
                when (statues) {
                    "progress" -> R.drawable.ic_time
                    "delivered" -> R.drawable.ic_check_blow
                    else -> R.drawable.ic_cancel
                }
            ),
            contentDescription = null,
            modifier = Modifier.size(18.dp),
            tint = when (statues){
                "progress" -> yelloColor
                "delivered" -> SkyColorBlue
                else -> Color.DarkGray
            }
        )

        AppSpacer(width = medium)
        NormalText(
            text = when (statues) {
                "progress" -> "قيد التنفيذ"
                "delivered" -> "تم الاستلام"
                else -> "ملغية"
            },
            fontSize = 14
        )
    }
}

@Preview
@Composable
private fun OrderStatuesViewPreview() {
    OrderStatuesView(
        statues = "progress"
    )
}
