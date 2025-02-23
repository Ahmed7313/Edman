package com.daman.edman.screens.Home.GarantieRequest

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aramex.mypos.Presentation.Components.MainEditTextFramed
import com.daman.edman.R
import com.daman.edman.screens.components.AppSpacer
import com.daman.edman.screens.components.HeaderText
import com.daman.edman.screens.components.IconTextView
import com.trend.camelx.ui.theme.large
import com.trend.camelx.ui.theme.spacing
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import com.daman.edman.screens.components.NormalText
import com.daman.edman.screens.components.SegmentedPills

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun GarantieRequestScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = spacing, end = spacing, top = large, bottom = large)

    ) {

        IconTextView(
            text = "عن المنتج",
            icon = R.drawable.ic_black_box
        )

        AppSpacer(height = large)

        var productDiscreption by remember { mutableStateOf("") }
        var productDiscreptionError by remember { mutableStateOf(false) }

        MainEditTextFramed(
            text = productDiscreption,
            onTextChange = {
                productDiscreption = it
                productDiscreptionError = false
            },
            isError = productDiscreptionError,
            label = "وصف المنتج",
            aboveText = "المنتج",
        )

        AppSpacer(height = large)

        var orderNumber by remember { mutableStateOf("") }
        var orderNumberError by remember { mutableStateOf(false) }

        MainEditTextFramed(
            text = orderNumber,
            onTextChange = {
                orderNumber = it
                orderNumberError = false
            },
            isError = orderNumberError,
            label = "أدخل رقم طلب مشترياتك هنا",
            aboveText = "رقم الطلب",
        )

        AppSpacer(height = large)

        var price by remember { mutableStateOf("") }
        var priceError by remember { mutableStateOf(false) }

        MainEditTextFramed(
            text = price,
            onTextChange = {
                price = it
                priceError = false
            },
            isError = priceError,
            label = "0.00 ",
            aboveText = "المبلغ",
            trailingIcon = {
                HeaderText(text = "EGP", fontSize = 14, modifier = Modifier.padding(end = 8.dp))
            }
        )

        AppSpacer(height = large)

        NormalText(text = "حالة المنتج", fontSize = 16)

        AppSpacer(height = large)

        val stateOfproducts = listOf("مستعمل","جديد")

        SegmentedPills(
            options = stateOfproducts,
            onOptionSelected = { selectedValue ->
                // Handle the selected value, e.g., update ViewModel or state
                println("Selected value: $selectedValue")
            }
        )

        AppSpacer(height = large)

        NormalText(text = "مدة التوصيل", fontSize = 16)

        AppSpacer(height = large)

        val options = listOf("3 أيام", "بعد يومان", "بعد يوم", "اليوم")

        SegmentedPills(
            options = options,
            onOptionSelected = { selectedValue ->
                // Handle the selected value, e.g., update ViewModel or state
                println("Selected value: $selectedValue")
            }
        )
    }
}