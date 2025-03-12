package com.daman.edman.screens.Profile.CreditCards

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.daman.edman.R
import com.daman.edman.screens.components.AppSpacerHeight
import com.daman.edman.screens.components.HeaderText
import com.daman.edman.screens.components.IconTextView
import com.daman.edman.screens.components.ToolBarView
import com.daman.edman.ui.theme.SkyColorBlue
import com.daman.edman.ui.theme.blueColor
import com.trend.camelx.ui.theme.large

@Preview(showBackground = true)
@Composable
private fun CreditCardsScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        ToolBarView("بطاقات الدفع")

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
                    textColor = blueColor
                )
            }

        }
    }
}