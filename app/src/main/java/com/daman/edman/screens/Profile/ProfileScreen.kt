package com.daman.edman.screens.Profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
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
import androidx.navigation.NavHostController
import com.aramex.mypos.Presentation.NavGrapghs.CreditCardsScreen
import com.aramex.mypos.Presentation.NavGrapghs.UserProfileScreen
import com.daman.edman.R
import com.daman.edman.screens.Home.GarantieRequest.PaymentView
import com.daman.edman.screens.components.AppSpacer
import com.daman.edman.screens.components.AppSpacerHeight
import com.daman.edman.screens.components.BorderView
import com.daman.edman.screens.components.HeaderText
import com.daman.edman.screens.components.NormalText
import com.daman.edman.screens.components.SettingsItem
import com.daman.edman.ui.theme.RedColor
import com.daman.edman.ui.theme.SkyColorBlue
import com.daman.edman.ui.theme.buttonColor
import com.daman.edman.ui.theme.grayColor
import com.daman.edman.ui.theme.lightGrayColor
import com.trend.camelx.ui.theme.large
import com.trend.camelx.ui.theme.medium
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    navHostController: NavHostController
) {

    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }
    var bottomSheetSource by remember { mutableStateOf("") }

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

            HorizontalDivider(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
            )
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
            ) {
                navHostController.navigate(UserProfileScreen)
            }

            AppSpacer(height = large)

            SettingsItem(
                text = "بطاقاتي",
                icon = R.drawable.ic_credit_card,
            ) {
                navHostController.navigate(CreditCardsScreen)
            }

            AppSpacerHeight()

            HeaderText(text = "الإعدادات", color = grayColor, fontSize = 16)

            AppSpacerHeight()


            SettingsItem(
                text = "اللغة",
                icon = R.drawable.ic_flag,
            ) {
                bottomSheetSource = "language"
                showBottomSheet = true
                scope.launch {
                    sheetState.show()
                }
            }

            AppSpacer(height = large)

            SettingsItem(
                text = "التنبيهات",
                icon = R.drawable.ic_notification,
            ) {
                bottomSheetSource = "notifications"
                showBottomSheet = true
                scope.launch {
                    sheetState.show()
                }
            }

            AppSpacer(height = large)

            SettingsItem(
                text = "أمان الحساب",
                icon = R.drawable.ic_lock,
            ) {
                bottomSheetSource = "accountSecurity"
                showBottomSheet = true
                scope.launch {
                    sheetState.show()
                }
            }

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

            AppSpacer(height = 110.dp)


            if (showBottomSheet) {
                ModalBottomSheet(
                    onDismissRequest = { showBottomSheet = false },
                    sheetState = sheetState,
                    containerColor = Color.White,
                ) {
                    BottomSheetView(bottomSheetSource, sheetState){
                        showBottomSheet = false
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetView(
    source: String,
    sheetState: SheetState,
    onDismiss : () -> Unit
) {

    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White)
            .padding(start = large, end = large, top = large, bottom = large)
    ) {

        HeaderText(
            text = "اللغة",
            fontSize = 16,
            color = grayColor,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        AppSpacerHeight()

        HorizontalDivider()

        when(source) {
            "language" -> {

                Column(modifier = Modifier.align(Alignment.CenterHorizontally)) {

                    AppSpacerHeight()


                    Row (verticalAlignment = Alignment.CenterVertically){
                        HeaderText(text = "العربية", fontSize = 16)
                        AppSpacer(width = large)
                        Icon(
                            painter = painterResource(R.drawable.ic_check_mark),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp),
                            tint = SkyColorBlue
                        )
                    }

                    AppSpacerHeight()
                    AppSpacerHeight()

                    HeaderText(text = "English", fontSize = 16, modifier = Modifier.align(Alignment.CenterHorizontally))


                }
            }
            "notifications" -> {

                AppSpacer(height = large)
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
                            HeaderText(text = "تنبيهات التطبيق ", fontSize = 14)

                            AppSpacer(height = large)

                            HeaderText(text = "السماح بالوصول إلى إعدادات التنبيهات", fontSize = 14, color = SkyColorBlue)

                            AppSpacer(height = medium)

                            NormalText(text = "باختيار هذا الرابط سوف يتم فتح إعدادات تنبيهات هاتفك لتسهيل عملية تفعيل استقبال تنبيهات مننا.")
                        }

                    }
                }

            }
            "accountSecurity" -> {
                AppSpacer(height = large)
                BorderView {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                    ) {

                        Icon(
                            painter = painterResource(id = R.drawable.ic_delete),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp),
                            tint = RedColor
                        )
                        AppSpacer(width = 8.dp)

                        Column {
                            HeaderText(text = "حذف الحساب", fontSize = 14)

                            AppSpacer(height = large)

                            HeaderText(text = "إيقاف  هذا الحساب عن العمل و حذفه", fontSize = 14, color = RedColor)

                            AppSpacer(height = medium)

                            NormalText(text = "باختيار هذا الخاصية سوف يتم إيقاف العمل بهذا الحساب نهائياً ( يمكنك استعادته خلال مدة أقصاها 30 يوم ). ")
                        }

                    }
                }
            }
        }

        AppSpacerHeight()

        HeaderText(
            text = "إلغاء",
            fontSize = 14,
            color = buttonColor,
            modifier = Modifier.align(Alignment.CenterHorizontally).clickable {
                scope.launch {
                    sheetState.hide()
                    onDismiss()
                }

            }
        )
        AppSpacer(height = 40.dp)

    }
}