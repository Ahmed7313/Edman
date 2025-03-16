package com.daman.edman.screens.Home.Notifications

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
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
import com.trend.camelx.ui.theme.spacing

@Composable
 fun NotificationsScreen(
     navHostController: NavHostController
 ) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        ToolBarView("التنبيهات",navHostController)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = spacing, end = spacing, top = large, bottom = large)
                .verticalScroll(rememberScrollState())

        ) {


            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                HeaderText(text = "تبنيهاتي", fontSize = 16, color = Color.Gray)

                NormalText(text = "حذف الكل", color = blueColor)
            }

            AppSpacerHeight()

            NotificationItem(
                date = " Jan 16,2024",
                title = "تنبيه",
                discreption = "هذا النص هو مثال لنص يمكن أن يستبدل في نفس المساحة."
            )

            AppSpacerHeight()

            NotificationItem(
                date = " Jan 16,2024",
                title = "طلب جديد قيد التنفيذ",
                discreption = "هذا النص هو مثال لنص يمكن أن يستبدل في نفس المساحة."
            )
        }
    }
}

@Composable
 fun NotificationItem(
     date : String,
     title : String,
     discreption : String
 ) {

     BorderView {
         Row(
             modifier = Modifier.fillMaxWidth(),
         ) {

             Icon(
                 painter = painterResource(id = R.drawable.ic_notification),
                 contentDescription = null,
                 modifier = Modifier.size(24.dp),
             )
             AppSpacer(width = 8.dp)

             Column {
                 NormalText(text = date, fontSize = 12)

                 AppSpacer(height = large)

                 HeaderText(text = title, fontSize = 14)

                 AppSpacer(height = medium)

                 NormalText(text =discreption)
             }

         }
     }
}