package com.daman.edman.screens.Wallet

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.daman.edman.R
import com.daman.edman.screens.components.AppSpacer
import com.daman.edman.screens.components.AppSpacerHeight
import com.daman.edman.screens.components.AppToolBar
import com.daman.edman.screens.components.BorderView
import com.daman.edman.screens.components.HeaderText
import com.daman.edman.screens.components.NormalText
import com.daman.edman.ui.theme.SkyColorBlue
import com.daman.edman.ui.theme.lightGrayColor
import com.trend.camelx.ui.theme.large
import com.trend.camelx.ui.theme.medium
import com.trend.thecontent.screens.components.MainButton

@Preview(showBackground = true)
@Composable
fun WalletScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = lightGrayColor)
    ) {
        AppToolBar(backView = true) {

        }

        AppSpacerHeight()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = lightGrayColor)
                .padding(large)
        ) {
            BorderView {
                HeaderText(text = "رصيدك المتاح", fontSize = 12)
                AppSpacer(height = large)
                HeaderText(text = "0.0 EGP", fontSize = 32, color = SkyColorBlue)

                AppSpacer(height = large)

                MainButton(text = "شحن المحفظة") { }
            }

            AppSpacerHeight()

            NormalText(text = "عملياتك السابقة", fontSize = 16)

            LazyColumn {
                item {

                    AppSpacerHeight()

                    WalletItem(
                        headerText = "استلام دفع من مشتري",
                        subText = "تم استلام 500 EGP من محمد علي رقم المحفظة   01155487795"
                    )
                }
            }
        }
    }
}

@Composable
fun WalletItem(
    modifier: Modifier = Modifier,
    headerText: String,
    subText: String,
) {
    BorderView {
        Row(
            modifier = modifier.fillMaxWidth(),
        ) {

            Image(
                painter = painterResource(id = R.drawable.ic_dollar_shield),
                contentDescription = null,
                modifier = Modifier.size(24.dp),
            )
            AppSpacer(width = 8.dp)

            Column {

                HeaderText(text = headerText, fontSize = 16)

                AppSpacer(height = large)

                NormalText(text = subText)
            }

        }
    }
}


@Preview
@Composable
fun WalletItemPreview() {
    WalletItem(
        headerText = "مدة التوصيل",
        subText = "من 3 الى 5 ايام"
    )
}