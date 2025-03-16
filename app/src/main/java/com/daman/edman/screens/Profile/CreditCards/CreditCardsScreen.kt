package com.daman.edman.screens.Profile.CreditCards

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.aramex.mypos.Presentation.NavGrapghs.AddCardScreen
import com.daman.edman.R
import com.daman.edman.screens.components.AppSpacer
import com.daman.edman.screens.components.AppSpacerHeight
import com.daman.edman.screens.components.BorderView
import com.daman.edman.screens.components.HeaderText
import com.daman.edman.screens.components.IconTextView
import com.daman.edman.screens.components.NormalText
import com.daman.edman.screens.components.ToolBarView
import com.daman.edman.ui.theme.SkyColorBlue
import com.daman.edman.ui.theme.blueColor
import com.trend.camelx.ui.theme.large
import com.trend.camelx.ui.theme.medium

@Composable
 fun CreditCardsScreen(
    navHostController: NavHostController
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        ToolBarView("بطاقات الدفع", navHostController)

        AppSpacerHeight()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)
                .padding(start = large, end = large, top = large, bottom = large)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                HeaderText(text = "البطاقات", fontSize = 16, color = Color.Gray)

                IconTextView(
                    text = "إضافة بطاقة",
                    icon = R.drawable.ic_add,
                    tint = blueColor,
                    textColor = blueColor,
                    modifier = Modifier.clickable {
                        navHostController.navigate(AddCardScreen)
                    }
                )
            }

            AppSpacerHeight()

            CardItem()

        }
    }
}

@Preview
@Composable
private fun CardItem() {

    BorderView {
        Row (modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically){

            Image(
                painter = painterResource(R.drawable.visa),
                contentDescription = null,
                modifier = Modifier.size(51.dp)
            )

            AppSpacer(width = medium)

            Column {
                HeaderText(text = "Visa xxxx2014", fontSize = 14)
                AppSpacer(height = large)
                NormalText(text = "تاريخ الانتهاء 12/2023", fontSize = 12)
            }
        }
    }
}