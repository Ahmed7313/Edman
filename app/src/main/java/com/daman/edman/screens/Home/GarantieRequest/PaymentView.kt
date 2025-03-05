package com.daman.edman.screens.Home.GarantieRequest

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.daman.edman.R
import com.daman.edman.screens.components.AppSpacer
import com.daman.edman.screens.components.HeaderText
import com.daman.edman.screens.components.NormalText
import com.trend.camelx.ui.theme.large
import com.trend.camelx.ui.theme.spacing

@Preview(showBackground = true)
@Composable
private fun PaymentView() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = spacing, end = spacing, top = large, bottom = large)

    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_1_1),
            contentDescription = null,
            modifier = Modifier
                .size(50.dp)
        )

        AppSpacer(height = 24.dp)

        HeaderText(text = "أكد الشحن و استلم فلوسك مع اضمن", fontSize = 16)
        AppSpacer(height = large)
        NormalText(text = "قم بتأكيد شحنك لطلب المشتري  و استلم فلوسك مع اضمن", fontSize = 14)

        AppSpacer(height = spacing)
    }
}