package com.daman.edman.screens.Auth.Login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.daman.edman.R
import com.daman.edman.screens.components.AppSpacer
import com.daman.edman.screens.components.HeaderText
import com.daman.edman.screens.components.NormalText
import com.daman.edman.ui.theme.SkyColorBlue
import com.daman.edman.ui.theme.lightGrayColor
import com.trend.camelx.ui.theme.large
import com.trend.camelx.ui.theme.medium
import com.trend.camelx.ui.theme.spacing

@Preview(showBackground = true)
@Composable
fun RegisterScreen() {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {

        // Semi-transparent overlay to make the image look shady
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.8f))
        )

        // Background Image with shading overlay
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.darkened_first_image),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
            )
        }

        // Your content goes here
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp), // Add padding if needed
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AppSpacer(height = 80.dp)

            Image(
                painter = painterResource(id = R.drawable.logo_1_1),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
            )

            AppSpacer(height = 40.dp)

            HeaderText(
                text = stringResource(R.string.welcome_to_edman),
                textAlign = TextAlign.Center,
                fontSize = 32,
                color = Color.White
            )

            AppSpacer(height = large)

            NormalText(
                text = "اشتري طلبك و اضمن وصوله بطريقة سهلة و فعالة.",
                fontSize = 18,
                color = lightGrayColor
            )

            AppSpacer(height = spacing)

            IconButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White, shape = RoundedCornerShape(spacing))
                    .padding(horizontal = spacing),
                onClick = { /*TODO*/ }
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.google_logo),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )

                    AppSpacer(width = medium)

                    HeaderText(text = "المتابعة إلى Gmail", fontSize = 16, color = Color.Black)
                }
            }

            AppSpacer(height = large)

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = spacing),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {

                HeaderText(text = "لديك حساب بالفعل؟", fontSize = 16, color = lightGrayColor)

                HeaderText(
                    text = "تسجيل الدخول",
                    fontSize = 16,
                    color = SkyColorBlue,
                    modifier = Modifier.clickable { })

            }
        }

        NormalText(
            text = "بالمتابعة في عملية تسجيل الدخول ستكون قد وافقت على",
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 60.dp)
        )

        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 40.dp)
        ) {
            NormalText(
                text = "شروط الخدمة",
                modifier = Modifier.clickable { },
                color = SkyColorBlue
            )

            NormalText(
                text = " و ",
                modifier = Modifier,
                color = SkyColorBlue
            )

            NormalText(
                text = "سياسة الخصوصية",
                modifier = Modifier.clickable { },
                color = SkyColorBlue
            )
        }


    }
}

