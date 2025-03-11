package com.daman.edman.screens.MyOrders.orderdetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.daman.edman.R
import com.daman.edman.screens.components.ToolBarView
import com.daman.edman.ui.theme.lightGrayColor
import com.daman.edman.ui.theme.yelloColor
import com.trend.camelx.ui.theme.large
import com.trend.camelx.ui.theme.spacing

@Preview
@Composable
fun OrderDetailsScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {

        ToolBarView("تفاصيل طلب")

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = spacing, end = spacing, top = large, bottom = large)
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_black_box),
                contentDescription = null,
                tint = yelloColor,
                modifier = Modifier.size(40.dp)
            )
        }


    }


}