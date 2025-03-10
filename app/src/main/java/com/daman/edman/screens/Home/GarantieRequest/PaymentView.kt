package com.daman.edman.screens.Home.GarantieRequest

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.daman.edman.R
import com.daman.edman.screens.components.AppSpacer
import com.daman.edman.screens.components.AppSpacerHeight
import com.daman.edman.screens.components.HeaderText
import com.daman.edman.screens.components.IconTextView
import com.daman.edman.screens.components.NormalText
import com.daman.edman.ui.theme.SkyColorBlue
import com.daman.edman.ui.theme.buttonColor
import com.daman.edman.ui.theme.lightBlue
import com.trend.camelx.ui.theme.large
import com.trend.camelx.ui.theme.spacing
import com.trend.thecontent.screens.components.MainButton

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

        IconTextView(
            text = "ملخص الطلب",
            icon = R.drawable.ic_file,
        )

        AppSpacerHeight()

        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(large),
            border = BorderStroke(1.dp, Color.Gray)
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(top = spacing, bottom = spacing, start = large, end = large)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    NormalText(text = "رقم الطلب")
                    NormalText(text = "123456789")
                }

                AppSpacerHeight()

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    NormalText(text = "رقم بوليصة الشحن")
                    NormalText(text = "ADXS7784244")
                }

                AppSpacerHeight()

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    NormalText(text = "سعر طلب المشتري")
                    NormalText(text = "500")
                }
            }

        }

        AppSpacer(height = spacing)

        IconTextView(
            text = "ملخص الدفع",
            icon = R.drawable.ic_payment_card,
        )

        AppSpacerHeight()

        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(large),
            border = BorderStroke(1.dp, Color.Gray)
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(top = spacing, bottom = spacing, start = large, end = large)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    NormalText(text = "المبلغ المستلم")
                    NormalText(text = "123456789")
                }

                AppSpacerHeight()

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row { NormalText(text = "سعر الخدمة (20%)")
                        AppSpacer(width = 8.dp)
                        Icon(painter = painterResource(R.drawable.ic_wonder_mark),
                            contentDescription = null,
                            Modifier.size(15.dp),
                            tint = SkyColorBlue)
                    }
                    NormalText(text = "ADXS7784244")
                }

                AppSpacerHeight()

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    HeaderText(text = "الإجمالي", fontSize = 14)
                    HeaderText(text = "500 EGP", fontSize = 14)
                }
            }
        }

        AppSpacerHeight()

        MainButton(text = "تأكيد الطلب") { }

        AppSpacerHeight()

        HeaderText(
            text = "إلغاء",
            fontSize = 16,
            color = buttonColor,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}