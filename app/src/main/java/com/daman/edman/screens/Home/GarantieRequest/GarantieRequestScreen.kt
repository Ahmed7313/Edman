package com.daman.edman.screens.Home.GarantieRequest

import ImagePicker
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import com.daman.edman.screens.components.AppSpacerHeight
import com.daman.edman.screens.components.DatePickerView
import com.daman.edman.screens.components.NormalText
import com.daman.edman.screens.components.SegmentedPills
import com.daman.edman.ui.theme.buttonColor
import com.trend.thecontent.screens.components.MainButton

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun GarantieRequestScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = spacing, end = spacing, top = large, bottom = large)
            .verticalScroll(rememberScrollState())

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

        AppSpacer(height = large)

        NormalText(text = "أو", modifier = Modifier.align(Alignment.CenterHorizontally))

        AppSpacer(height = large)

        DatePickerView {

        }

        AppSpacerHeight()

        IconTextView(
            text = "مشاركة روابط",
            icon = R.drawable.ic_link_sharing
        )

        AppSpacerHeight()

        var productLink by remember { mutableStateOf("") }
        var productLinkError by remember { mutableStateOf(false) }

        MainEditTextFramed(
            text = productLink,
            onTextChange = {
                productLink = it
                productLinkError = false
            },
            isError = productLinkError,
            label = "يمكنك مشاركة رابط طلب منتجك هنا",
            aboveText = " رابط طلب منتجك",
        )

        AppSpacerHeight()

        var pageLink by remember { mutableStateOf("") }
        var pageLinkError by remember { mutableStateOf(false) }

        MainEditTextFramed(
            text = pageLink,
            onTextChange = {
                pageLink = it
                pageLinkError = false
            },
            isError = pageLinkError,
            label = " رابط  الصفحة الرسمية",
            aboveText = "يمكنك مشاركة رابط الصفحة الرسمية للبائع هنا",
        )

        AppSpacerHeight()

        IconTextView(
            text = "مشاركة صور الطلب",
            icon = R.drawable.ic_camera
        )

        AppSpacerHeight()

        ImagePicker(
            icon = R.drawable.ic_product_image,
            text = "صورة للمنتج",
            secText = "قم تحميل صورة منتجك هنا"
        ) { }

        AppSpacerHeight()

        ImagePicker(
            icon = R.drawable.ic_product_order,
            text = "صورة لطلب المنتج ",
            secText = "قم تحميل صورة لطلب المنتج هنا"
        ) { }

        AppSpacerHeight()

        var isChecked = remember { mutableStateOf(false) }

        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Checkbox that updates the state when clicked
            Checkbox(
                checked = isChecked.value,
                onCheckedChange = {
                    isChecked.value = it
                }
            )

            // Text next to the checkbox
            HeaderText(
                text = "باختيار هذه الخاصية سوف يتم تأكيد شحن الطلب و تم استلامه من قبل المشتري و هذا ضمان لعميلة الدفع.",
                modifier = Modifier.padding(start = 8.dp),
                color = Color.Gray,
                fontSize = 14
            )
        }

        AppSpacerHeight()

        ImagePicker(
            icon = R.drawable.ic_blue_check,
            text = "التأكيد بصورة لبوليصة الشحن ",
            secText = "يرجى تأكيد الشحن وإرسال الطلب للمشتري  بصورة بوليصة الشحن."
        ) { }

        AppSpacerHeight()

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
