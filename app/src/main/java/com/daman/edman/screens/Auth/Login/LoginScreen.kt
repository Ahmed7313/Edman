package com.daman.edman.screens.Auth.Login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.daman.edman.R
import com.daman.edman.components.AppHolder
import com.daman.edman.components.AppSpacer
import com.daman.edman.components.HeaderText
import com.daman.edman.components.NormalText
import com.trend.camelx.ui.theme.large


@Preview
@Composable
private fun LoginScreen() {

    AppHolder {

        AppSpacer(height = 80.dp)

        Image(
            painter = painterResource(id = R.drawable.logo_1_1),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
        )

        AppSpacer(height = 34.dp)

        HeaderText(text = "تسجيل الدخول", fontSize = 24)

        AppSpacer(height = large)

        NormalText(text = "قم بتسجيل الدخول عن طريق رقم الهاتف او من خلال بريدك الإلكتروني.")


    }
}