package com.daman.edman.screens.Home.CreateRequest

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
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
import com.daman.edman.R
import com.daman.edman.screens.components.AppSpacer
import com.daman.edman.screens.components.HeaderText
import com.daman.edman.screens.components.IconTextView
import com.daman.edman.screens.components.NormalText
import com.daman.edman.screens.components.UserInfoItem
import com.daman.edman.ui.theme.SkyColor
import com.daman.edman.ui.theme.borderColor
import com.daman.edman.ui.theme.buttonColor
import com.trend.camelx.ui.theme.large
import com.trend.camelx.ui.theme.medium
import com.trend.camelx.ui.theme.spacing
import com.trend.thecontent.screens.components.MainButton

@Preview(showBackground = true)
@Composable
fun RequestScreen() {
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

        HeaderText(text = "اطلب خدمتك مع اضمن", fontSize = 16)
        AppSpacer(height = large)
        NormalText(
            text = "يمكنك طلب هذه الخدمة لضمان وصول شحنتك أو استلام فلوسك بطريقة فعالة و سهلة.",
            fontSize = 14
        )

        AppSpacer(height = spacing)

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            HeaderText(text = "بياناتك", fontSize = 16)

            HeaderText(text = "تعديل", fontSize = 14, color = SkyColor)
        }

        AppSpacer(height = large)

        UserInfoItem(
            name = "محمد عبد الرحمن",
            phone = "01000000000",
            email = "ahmedraboe@gmail.com",
            id = "123456789012345"
        )


        AppSpacer(height = spacing)

        HeaderText(text = "نوع ضمانك", fontSize = 16)


        AppSpacer(height = large)

        GuaranteeSelectionScreen()

        AppSpacer(height = spacing)


        MainButton(
            text = "المتابعة"
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

// Main Screen
@Composable
fun GuaranteeSelectionScreen() {
    var selectedTab by remember { mutableStateOf(0) }

    Column {
        // Selection Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            SelectionButton(
                modifier = Modifier.weight(1f),
                text = "ضمان دفع",
                icon = R.drawable.ic_cash,
                isSelected = selectedTab == 0,
                onSelect = { selectedTab = 0 }
            )

            Spacer(modifier = Modifier.width(8.dp))

            SelectionButton(
                modifier = Modifier.weight(1f),
                text = "ضمان وصول",
                isSelected = selectedTab == 1,
                onSelect = { selectedTab = 1 },
                icon = R.drawable.ic_box
            )
        }


        // Dynamic Content
        when (selectedTab) {
            0 -> PaymentGuaranteeContent()
            1 -> ReceiptGuaranteeContent()
        }
    }
}

// Selection Button Component
@Composable
fun SelectionButton(
    text: String,
    modifier: Modifier = Modifier,
    icon: Int,
    isSelected: Boolean,
    onSelect: () -> Unit
) {
    val backgroundColor = if (isSelected) Color.Blue else Color.LightGray
    val textColor = if (isSelected) Color.White else Color.DarkGray

    Row(
        modifier = modifier
            .height(48.dp)
            .clickable {
                onSelect()
            }
            .background(
                color = if (isSelected) buttonColor else Color.White,
                shape = RoundedCornerShape(spacing)
            )
            .border(
                width = 1.dp,
                color = if (isSelected) buttonColor else borderColor,
                shape = RoundedCornerShape(spacing)
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        NormalText(text = text, color = if (isSelected) Color.White else Color.Black)
        AppSpacer(width = medium)
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            modifier = Modifier.size(24.dp),
            tint = if (isSelected) Color.White else Color.Black
        )
    }
}

// Content Screens
@Composable
fun PaymentGuaranteeContent() {
    // Your payment guarantee UI here
    Column(modifier = Modifier.fillMaxWidth()) {
        HeaderText(text = "بائع مشترياتك")
        AppSpacer(height = large)
        var searchQuery by remember { mutableStateOf("") }
        MainEditText(
            text = searchQuery,
            onTextChange = { searchQuery = it },
            label = "عن من تبحث؟",
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(spacing),
            isError = false,
            leadingIcon = {
                Image(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = null,
                    modifier = Modifier.size(17.dp)
                )
            }
        )

        AppSpacer(height = medium)

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconTextView(
                text = "بيانات البائع",
                icon = R.drawable.ic_truck
            )

            Icon(
                painter = painterResource(id = R.drawable.ic_mark),
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
        }

        AppSpacer(height = medium)

        UserInfoItem(
            name = "محمد عبد الرحمن",
            phone = "01000000000",
            email = "asda@gmail.com",
            id = "123456789012345"
        )

    }

}

@Composable
fun ReceiptGuaranteeContent() {

    Column(modifier = Modifier.fillMaxWidth()) {
        HeaderText(text = "بائع مشترياتك")
        AppSpacer(height = large)
        var searchQuery by remember { mutableStateOf("") }
        MainEditText(
            text = searchQuery,
            onTextChange = { searchQuery = it },
            label = "عن من تبحث؟",
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(spacing),
            isError = false,
            leadingIcon = {
                Image(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = null,
                    modifier = Modifier.size(17.dp)
                )
            }
        )

        AppSpacer(height = medium)

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconTextView(
                text = "بيانات البائع",
                icon = R.drawable.ic_truck
            )

            Icon(
                painter = painterResource(id = R.drawable.ic_mark),
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
        }

        AppSpacer(height = medium)

        UserInfoItem(
            name = "محمد عبد الرحمن",
            phone = "01000000000",
            email = "asda@gmail.com",
            id = "123456789012345"
        )

    }

}
