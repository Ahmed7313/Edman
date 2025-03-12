package com.daman.edman.screens.Profile.userProfile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.daman.edman.R
import com.daman.edman.screens.components.AppSpacerHeight
import com.daman.edman.screens.components.HeaderText
import com.daman.edman.screens.components.ToolBarView
import com.daman.edman.ui.theme.SkyColorBlue
import com.trend.camelx.ui.theme.large

@Preview(showBackground = true)
@Composable
fun UserProfileScreen() {


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        ToolBarView("الملف الشخصي")

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
                HeaderText(text = "بياناتك", fontSize = 16, color = Color.Gray)

                HeaderText(text = "تعديل", fontSize = 14, color = SkyColorBlue)
            }

            AppSpacerHeight()

            Box(
                modifier = Modifier
                    .size(120.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                Image(
                    painter = painterResource(R.drawable.logo),
                    contentDescription = null,
                    modifier = Modifier
                        .alpha(0.5f)
                        .size(120.dp)
                        .align(Alignment.Center)
                        .border(width = 1.dp, color = Color.Gray, shape = CircleShape)

                )

                Icon(
                    painter = painterResource(R.drawable.ic_camera_icon),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier
                        .size(30.dp)
                        .clip(CircleShape)
                        .align(Alignment.BottomEnd)
                        .background(shape = CircleShape, color = SkyColorBlue)
                )
            }
        }
    }
}
