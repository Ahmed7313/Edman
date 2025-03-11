package com.daman.edman.screens.MyOrders

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.daman.edman.R
import com.daman.edman.screens.components.AppSpacer
import com.daman.edman.screens.components.BorderView
import com.daman.edman.screens.components.HeaderText
import com.daman.edman.screens.components.NormalText
import com.daman.edman.screens.components.OrderStatuesView
import com.daman.edman.ui.theme.SkyColorBlue
import com.daman.edman.ui.theme.lightGrayColor
import com.trend.camelx.ui.theme.large
import com.trend.camelx.ui.theme.medium
import com.trend.camelx.ui.theme.spacing


@Composable
fun OrderItem(
    modifier: Modifier = Modifier,
    statues : String,
    orderedIn : String,
    deliveryDuration : String,
    description : String,
    total : String,
    onClick : () -> Unit
) {

    BorderView (modifier = Modifier.clickable { onClick() }){

        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OrderStatuesView(
                statues = statues
            )
            AppSpacer(width = medium)

            NormalText(
                text = orderedIn,
                modifier = Modifier
                    .background(
                        color = lightGrayColor, shape = RoundedCornerShape(
                            large
                        )
                    )
                    .padding(medium)
            )
        }

        AppSpacer(height = large)

        Row(
            modifier = Modifier.fillMaxWidth(),
        ) {

                Icon(
                    painter = painterResource(id = R.drawable.ic_black_box),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                )
                AppSpacer(width = 8.dp)

                Column {
                    Row(
                        modifier = Modifier,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        HeaderText(text = "مدة التوصيل", fontSize = 14)
                        AppSpacer(width = medium)
                        HeaderText(text = deliveryDuration, color = SkyColorBlue, fontSize = 12)
                    }

                    AppSpacer(height = large)

                    HeaderText(text = "طلب ضمان وصول منتجات", fontSize = 14)

                    AppSpacer(height = medium)

                    NormalText(text = description)
                }

            }
        AppSpacer(height = large)

        HorizontalDivider()

        AppSpacer(height = spacing)

        Row (modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically){
            HeaderText(text = "المجموع", color = SkyColorBlue, fontSize = 14)
            HeaderText(text = "$total EGP", color = SkyColorBlue, fontSize = 14)
        }
    }
}

@Preview
@Composable
private fun OrderItemPreview() {
    OrderItem(
        statues = "progress",
        orderedIn = "منذ 3 ساعات",
        deliveryDuration = "3 أيام",
        description = "تم تأكيد الطلب",
        total = "500",
        onClick = {}
    )
}