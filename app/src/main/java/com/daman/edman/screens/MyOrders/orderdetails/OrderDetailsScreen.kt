package com.daman.edman.screens.MyOrders.orderdetails

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.daman.edman.R
import com.daman.edman.screens.Home.HomeViewModel
import com.daman.edman.screens.components.AppSpacer
import com.daman.edman.screens.components.AppSpacerHeight
import com.daman.edman.screens.components.BorderView
import com.daman.edman.screens.components.HeaderText
import com.daman.edman.screens.components.IconTextView
import com.daman.edman.screens.components.NormalText
import com.daman.edman.screens.components.OrderStatuesView
import com.daman.edman.screens.components.ToolBarView
import com.daman.edman.ui.theme.RedColor
import com.daman.edman.ui.theme.SkyColorBlue
import com.daman.edman.ui.theme.buttonColor
import com.daman.edman.ui.theme.grayColor
import com.daman.edman.ui.theme.lightGrayColor
import com.daman.edman.ui.theme.visaColor
import com.daman.edman.ui.theme.yelloColor
import com.trend.camelx.ui.theme.large
import com.trend.camelx.ui.theme.medium
import com.trend.camelx.ui.theme.small
import com.trend.camelx.ui.theme.spacing
import com.trend.thecontent.screens.components.MainButton

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun OrderDetailsScreen() {


    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {

        ToolBarView("تفاصيل طلب")

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(start = large, end = large, top = large, bottom = large)
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_black_box),
                contentDescription = null,
                tint = yelloColor,
                modifier = Modifier.size(40.dp)
            )

            AppSpacerHeight()

            HeaderText(text = "طلب ضمان وصول منتجات", fontSize = 14)

            AppSpacerHeight()

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OrderStatuesView(
                    statues = "progress"
                )
                AppSpacer(width = medium)

                NormalText(
                    text = "تم الطلب في Jan 16,2024",
                    modifier = Modifier
                        .background(
                            color = lightGrayColor, shape = RoundedCornerShape(
                                large
                            )
                        )
                        .padding(medium)
                )
            }

            AppSpacer(height = large)

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconTextView(
                    icon = R.drawable.ic_file,
                    text = "ملخص الطلب",
                )

                NormalText(
                    text = "إلغاء الطلب",
                    fontSize = 12,
                    color = RedColor,
                    modifier = Modifier.clickable {
                        showBottomSheet = true
                    })
            }

            AppSpacer(height = large)

            BorderView {
                NormalText(text = "رقم الطلب  0015584")
                AppSpacer(height = large)
                HeaderText(text = "AD555E12 - Bed 30×140×200cm - Beige - GO.W.2-2B", fontSize = 14)
                AppSpacer(height = large)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    NormalText(text = "جديد ")
                    AppSpacer(width = spacing)
                    NormalText(text = "مدة التوصيل", fontSize = 12)
                    AppSpacer(width = medium)
                    HeaderText(text = "اليوم", color = SkyColorBlue, fontSize = 12)
                }
            }

            AppSpacerHeight()

            IconTextView(
                icon = R.drawable.ic_link_sharing,
                text = "مشاركة روابط",
            )

            AppSpacerHeight()

            NormalText(text = "رابط طلب المنتج ")

            AppSpacerHeight()

            BorderView(shape = RoundedCornerShape(spacing)) {
                NormalText(
                    text = "//homzmart.com/en/p/red-beech-wood-l-shape-s...",
                    color = SkyColorBlue
                )
            }

            AppSpacerHeight()

            NormalText(text = " رابط  الصفحة الرسمية")

            AppSpacerHeight()

            BorderView(shape = RoundedCornerShape(spacing)) {
                NormalText(
                    text = "//homzmart.com/en/p/red-beech-wood-l-shape-s...",
                    color = SkyColorBlue
                )
            }

            AppSpacerHeight()

            IconTextView(
                icon = R.drawable.ic_black_check,
                text = "صورة لبوليصة الشحن ",
            )

            AppSpacerHeight()

            BorderView {
                Row(modifier = Modifier.fillMaxWidth()) {

                    Icon(
                        painter = painterResource(R.drawable.ic_pdf),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp),
                        tint = RedColor
                    )

                    AppSpacer(width = large)
                    Column {
                        HeaderText(
                            text = "Bed 30×140×200cm - Beige .pdf",
                            color = grayColor,
                            fontSize = 14
                        )
                        AppSpacer(height = small)
                        NormalText(text = "1 MB")
                    }

                }
            }


            AppSpacerHeight()

            IconTextView(
                icon = R.drawable.ic_payment_card,
                text = "ملخص الدفع",
            )

            AppSpacerHeight()

            BorderView {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    NormalText(text = "مبلغ  طلبك من البائع", fontSize = 14)

                    Row {
                        NormalText(text = "500")
                        AppSpacer(width = medium)
                        NormalText(text = "EGP", fontSize = 10)
                    }
                }

                AppSpacerHeight()

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    NormalText(text = "سعر الخدمة (20%)", fontSize = 14)

                    Row {
                        NormalText(text = "100")
                        AppSpacer(width = medium)
                        NormalText(text = "EGP", fontSize = 10)
                    }
                }

                AppSpacerHeight()

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    NormalText(text = "طريقة الدفع", fontSize = 14)

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        NormalText(text = " بطاقة xxx2014")
                        AppSpacer(width = medium)
                        Icon(
                            painter = painterResource(R.drawable.ic_visa),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp),
                            tint = visaColor
                        )
                    }
                }

                AppSpacerHeight()

                HorizontalDivider()

                AppSpacerHeight()

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    HeaderText(text = "الإجمالي", fontSize = 14)
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        HeaderText(text = "600", fontSize = 14)
                        AppSpacer(width = medium)
                        NormalText(text = "EGP", fontSize = 10)
                    }
                }
            }

        }

        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = { showBottomSheet = false },
                sheetState = sheetState,
                containerColor = Color.White,
            ) {
                BottomSheetView()
            }
        }

    }

}

@Preview
@Composable
fun BottomSheetView(
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(start = spacing, end = spacing, top = large, bottom = large)

    ) {

        Image(
            painter = painterResource(id = R.drawable.logo_1_1),
            contentDescription = null,
            modifier = Modifier
                .size(50.dp)
        )

        AppSpacer(height = 24.dp)

        HeaderText(text = "إلغاء الطلب", fontSize = 16)
        AppSpacer(height = large)
        NormalText(
            text = "بتأكيد إالغاء الطلب سوف يتم استرداد المبلغ المدفوع خلال مدة أقصاها 15 يوم.",
            fontSize = 14
        )

        AppSpacer(height = spacing)

        IconTextView(
            text = "ملخص الطلب",
            icon = R.drawable.ic_file,
        )

        AppSpacerHeight()

        BorderView {
            NormalText(text = "رقم الطلب  0015584")
            AppSpacer(height = large)
            HeaderText(text = "AD555E12 - Bed 30×140×200cm - Beige - GO.W.2-2B", fontSize = 14)
            AppSpacer(height = large)
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                NormalText(text = "جديد ")
                AppSpacer(width = spacing)
                NormalText(text = "مدة التوصيل", fontSize = 12)
                AppSpacer(width = medium)
                HeaderText(text = "اليوم", color = SkyColorBlue, fontSize = 12)
            }
        }

        AppSpacerHeight()

        IconTextView(
            icon = R.drawable.ic_payment_card,
            text = "ملخص الدفع",
        )

        AppSpacerHeight()

        BorderView {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                NormalText(text = "مبلغ  طلبك من البائع", fontSize = 14)

                Row {
                    NormalText(text = "500")
                    AppSpacer(width = medium)
                    NormalText(text = "EGP", fontSize = 10)
                }
            }

            AppSpacerHeight()

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                NormalText(text = "سعر الخدمة (20%)", fontSize = 14)

                Row {
                    NormalText(text = "100")
                    AppSpacer(width = medium)
                    NormalText(text = "EGP", fontSize = 10)
                }
            }

            AppSpacerHeight()

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                NormalText(text = "طريقة الدفع", fontSize = 14)

                Row(verticalAlignment = Alignment.CenterVertically) {
                    NormalText(text = " بطاقة xxx2014")
                    AppSpacer(width = medium)
                    Icon(
                        painter = painterResource(R.drawable.ic_visa),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp),
                        tint = visaColor
                    )
                }
            }

            AppSpacerHeight()

            HorizontalDivider()

            AppSpacerHeight()

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                HeaderText(text = "الإجمالي", fontSize = 14)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    HeaderText(text = "600", fontSize = 14)
                    AppSpacer(width = medium)
                    NormalText(text = "EGP", fontSize = 10)
                }
            }
        }


        AppSpacerHeight()

        MainButton(
            text = "تأكيد إلغاء الطلب"
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