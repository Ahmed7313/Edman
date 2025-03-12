package com.daman.edman.screens.Profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
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
import com.daman.edman.screens.components.SettingsItem
import com.daman.edman.ui.theme.RedColor
import com.daman.edman.ui.theme.buttonColor
import com.daman.edman.ui.theme.grayColor
import com.daman.edman.ui.theme.lightGrayColor
import com.trend.camelx.ui.theme.large

@Preview(showBackground = true)
@Composable
fun ProfileScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = lightGrayColor)
            .verticalScroll(rememberScrollState())
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .background(color = Color.White)
        ) {

            Image(
                painter = painterResource(id = R.drawable.ic_tool_bar_logo),
                contentDescription = null,
                modifier = Modifier
                    .width(100.dp)
                    .height(28.dp)
                    .align(Alignment.Center)
            )

            HorizontalDivider(modifier = Modifier.align(Alignment.BottomCenter).fillMaxWidth())
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = lightGrayColor)
                .padding(start = large, end = large, top = large, bottom = large)
        ) {

            AppSpacerHeight()

            HeaderText(text = "حسابي", color = grayColor, fontSize = 16)

            AppSpacerHeight()


            SettingsItem(
                text = "الملف الشخصي",
                icon = R.drawable.ic_profile,
             ){}

            AppSpacer(height = large)

            SettingsItem(
                text = "بطاقاتي",
                icon = R.drawable.ic_credit_card,
            ) { }

            AppSpacerHeight()

            HeaderText(text = "الإعدادات", color = grayColor, fontSize = 16)

            AppSpacerHeight()


            SettingsItem(
                text = "اللغة",
                icon = R.drawable.ic_flag,
            ){}

            AppSpacer(height = large)

            SettingsItem(
                text = "التنبيهات",
                icon = R.drawable.ic_notification,
            ) { }

            AppSpacer(height = large)

            SettingsItem(
                text = "أمان الحساب",
                icon = R.drawable.ic_lock,
            ) { }

            AppSpacerHeight()

            HeaderText(text = "المساعدة", color = grayColor, fontSize = 16)

            AppSpacerHeight()

            SettingsItem(
                text = "تواصل معنا",
                icon = R.drawable.ic_headphone,
            ) { }

            AppSpacerHeight()

            HeaderText(text = "المزيد", color = grayColor, fontSize = 16)

            AppSpacerHeight()

            SettingsItem(
                text = "سياسة الخصوصية",
                icon = R.drawable.ic_wonder_shield,
            ) { }

            AppSpacer(height = large)

            SettingsItem(
                text = "الشروط والأحكام",
                icon = R.drawable.ic_text,
            ) { }

            AppSpacerHeight()

            HeaderText(
                text = "تسجيل الخروج",
                fontSize = 16,
                color = RedColor,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            AppSpacerHeight()

        }
    }
}