package com.daman.edman.screens.Wallet.ChargeWallet

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aramex.mypos.Presentation.Components.MainEditText
import com.aramex.mypos.Presentation.Components.MainEditTextFramed
import com.aramex.mypos.Presentation.Components.MainEditTextWithoutIcon
import com.daman.edman.R
import com.daman.edman.screens.components.AppSpacer
import com.daman.edman.screens.components.AppSpacerHeight
import com.daman.edman.screens.components.BorderView
import com.daman.edman.screens.components.HeaderText
import com.daman.edman.screens.components.IconTextView
import com.daman.edman.screens.components.NormalText
import com.daman.edman.screens.components.ToolBarView
import com.daman.edman.ui.theme.buttonColor
import com.daman.edman.ui.theme.grayColor
import com.trend.camelx.ui.theme.large
import com.trend.camelx.ui.theme.medium
import com.trend.camelx.ui.theme.spacing
import com.trend.thecontent.screens.components.MainButton

@Preview(showBackground = true)
@Composable
private fun ChargeWalletScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {

        ToolBarView("شحن رصيد")

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(start = large, end = large, top = large, bottom = large)
        ) {

            Image(
                painter = painterResource(id = R.drawable.logo_1_1),
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
            )

            AppSpacer(height = 24.dp)

            HeaderText(text = "شحن رصيدك في اضمن ", fontSize = 16)
            AppSpacer(height = large)
            NormalText(
                text = "يمكنك شحن رصيدك في اضمن من خلال الخطوات التالية:",
                fontSize = 14
            )

            AppSpacer(height = spacing)

            IconTextView(
                icon = R.drawable.ic_money_black,
                text = "الرصيد"
            )

            AppSpacerHeight()

            BorderView {

                Row (verticalAlignment = Alignment.CenterVertically){
                    NormalText(text = "الرصيد", fontSize = 12)
                    AppSpacer(width = medium)
                    Icon(
                        painter = painterResource(R.drawable.ic_wonder_mark),
                        contentDescription = null,
                        modifier = Modifier.size(20.dp),
                        tint = grayColor
                    )
                }

                AppSpacerHeight()


                Row (verticalAlignment = Alignment.CenterVertically) {
                    var price by remember { mutableStateOf("") }
                    var priceError by remember { mutableStateOf(false) }
                    MainEditTextWithoutIcon(
                        text = price,
                        onTextChange = {
                            price = it
                            priceError = false
                        },
                        isError = priceError,
                        eraseBorder = true,
                        label = "0.00",
                        modifier = Modifier.wrapContentWidth().width(150.dp)
                    )

                    HeaderText(text = "EGP", fontSize = 12, modifier = Modifier.weight(1f).padding(top = 12.dp))
                }
            }


            AppSpacer(height = 90.dp)

            MainButton(
                text = "اشحن  الآن"
            ) {

            }

            AppSpacer(height = spacing)

            HeaderText(
                text = "إلغاء",
                fontSize = 16,
                color = buttonColor,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }

    }
}