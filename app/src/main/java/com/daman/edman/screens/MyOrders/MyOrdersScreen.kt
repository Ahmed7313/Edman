package com.daman.edman.screens.MyOrders

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aramex.mypos.Presentation.Components.MainEditText
import com.daman.edman.R
import com.daman.edman.screens.components.AppSpacer
import com.daman.edman.screens.components.CheckedItem
import com.daman.edman.screens.components.HeaderText
import com.daman.edman.screens.components.IconTextView
import com.daman.edman.screens.components.NormalText
import com.daman.edman.ui.theme.SkyColor
import com.daman.edman.ui.theme.SkyColorBlue
import com.daman.edman.ui.theme.borderColor
import com.daman.edman.ui.theme.buttonColor
import com.daman.edman.ui.theme.grayColor
import com.trend.camelx.ui.theme.large
import com.trend.camelx.ui.theme.medium
import com.trend.camelx.ui.theme.spacing
import com.trend.thecontent.screens.components.LoadingView
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import androidx.compose.material3.*
import com.daman.edman.screens.components.AppSpacerHeight


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun MyOrdersScreen(modifier: Modifier = Modifier) {


    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = spacing, end = spacing, top = large, bottom = large)

    ) {
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = spacing)
                    .clickable {
                        showBottomSheet = true
                    },
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                HeaderText(text = "الطلبات", fontSize = 16)

                IconTextView(
                    icon = R.drawable.ic_filter,
                    text = "تصفية الطلبات",
                    textColor = SkyColorBlue,
                    tint = SkyColorBlue
                )
            }

            AppSpacer(height = spacing)

            HorizontalDivider()

            AppSpacer(height = large)

            var searchQuery by remember { mutableStateOf("") }
            MainEditText(
                text = searchQuery,
                onTextChange = { searchQuery = it },
                label = "البحث...",
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

            AppSpacer(height = large)


            HeaderText(text = "قيد التنفيذ ( 1 )", color = grayColor, fontSize = 16)

            AppSpacer(height = large)

            OrderItem(
                statues = "progress",
                orderedIn = "تم الطلب في Jan 16,2024",
                deliveryDuration = "اليوم",
                description = "طلبك برقم 0015584 لضمان وصول شحنتك ( Bed 30×140×200cm - Beige - GO.W.2-2B )",
                total = "500",
                onClick = {}
            )

            AppSpacer(height = large)


            HeaderText(text = "مكتملة ", color = grayColor, fontSize = 16)

            AppSpacer(height = large)

            OrderItem(
                statues = "delivered",
                orderedIn = "تم الطلب في Jan 16,2024",
                deliveryDuration = "اليوم",
                description = "طلبك برقم 0015584 لضمان وصول شحنتك ( Bed 30×140×200cm - Beige - GO.W.2-2B )",
                total = "500",
                onClick = {}
            )

            AppSpacer(height = large)

            OrderItem(
                statues = "canceled",
                orderedIn = "تم الطلب في Jan 16,2024",
                deliveryDuration = "اليوم",
                description = "طلبك برقم 0015584 لضمان وصول شحنتك ( Bed 30×140×200cm - Beige - GO.W.2-2B )",
                total = "500",
                onClick = {}
            )

        }


        item {
            if (showBottomSheet) {
                ModalBottomSheet(
                    onDismissRequest = { showBottomSheet = false },
                    sheetState = sheetState,
                    containerColor = Color.White,
                ) {
                    com.daman.edman.screens.MyOrders.orderdetails.BottomSheetView()
                }
            }
        }

    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun BottomSheetView() {
    val options = listOf("جميع الطلبات", "قيد التنفيذ", "المكتملة", "الملغية")
    var selectedOption by remember { mutableStateOf(options[0]) }

    Column(modifier = Modifier.selectableGroup()) {
        options.forEach { option ->
            val selected = option == selectedOption
            ListItem(
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .selectable(
                        selected = selected,
                        onClick = { selectedOption = option }
                    ),
                headlineContent = {
                    Text(
                        text = option,
                        color = if (selected) Color.Blue else Color.Black
                    )
                },
                trailingContent = {
                    if (selected) {
                        Icon(imageVector = Icons.Default.Check, contentDescription = "Selected")
                    }
                }
            )
            if (option != options.last()) Divider()
        }

        AppSpacerHeight()
        HeaderText(
            text = "إلغاء",
            fontSize = 16,
            color = buttonColor,
            modifier = Modifier.align(Alignment.CenterHorizontally).clickable {

            }
        )
    }
}

@Composable
@Preview(showBackground = true)
fun DefaultPreview() {
    BottomSheetView()
}
