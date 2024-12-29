package com.daman.edman.screens.Home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.daman.edman.R
import com.daman.edman.screens.components.AppHolder
import com.daman.edman.screens.components.AppSpacer
import com.daman.edman.screens.components.HeaderText
import com.daman.edman.screens.components.NormalText
import com.daman.edman.ui.theme.lightGrayColor
import com.trend.camelx.ui.theme.large
import com.trend.camelx.ui.theme.medium
import com.trend.camelx.ui.theme.spacing
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.hilt.navigation.compose.hiltViewModel
import com.aramex.mypos.Presentation.Components.MainEditText
import com.daman.edman.screens.Home.HomeViewModel.CompleteUserModel
import com.daman.edman.screens.components.AppToolBar
import com.daman.edman.ui.theme.SkyColor
import com.daman.edman.ui.theme.borderColor
import com.daman.edman.ui.theme.buttonColor
import com.trend.thecontent.screens.components.LoadingView
import com.trend.thecontent.screens.components.MainButton
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = lightGrayColor)
    ) {

        val sheetState = rememberModalBottomSheetState()
        val scope = rememberCoroutineScope()
        var showBottomSheet by remember { mutableStateOf(false) }

        AppToolBar {}



        AppSpacer(height = large)

        AppHolder {

            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    color = Color.White,
                    shadowElevation = 4.dp,
                    shape = RoundedCornerShape(large)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = large, top = medium),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Column(modifier = Modifier.weight(3f)) {
                            HeaderText(text = "مرحباً بك", fontSize = 18)
                            AppSpacer(height = medium)
                            NormalText(text = "حدد خدمتك و تابع طلبك بوجود كل البيانات لضمان تجربتك مع اضمن.")
                        }
                        Image(
                            painter = painterResource(id = R.drawable.slogan),
                            contentDescription = null,
                            modifier = Modifier
                                .weight(1f)
                                .align(Alignment.Bottom)
                        )
                    }
                }

                AppSpacer(height = large)

                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    color = Color.White,
                    shadowElevation = 4.dp,
                    shape = RoundedCornerShape(large)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = large, top = medium, end = large, bottom = medium)
                    ) {

                        Image(
                            painter = painterResource(id = R.drawable.logo),
                            contentDescription = null,
                            modifier = Modifier
                                .size(40.dp)
                        )

                        HeaderText(text = "اطلب خدمتك مع اضمن ", fontSize = 18)

                        AppSpacer(height = medium)

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

                        AppSpacer(height = large)

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Row(
                                modifier = Modifier
                                    .weight(1f)
                                    .height(48.dp)
                                    .clickable {
                                        if (!viewModel.userFinishedOnBoarding()) {
                                            showBottomSheet = true
                                        }
                                    }
                                    .background(
                                        color = buttonColor,
                                        shape = RoundedCornerShape(spacing)
                                    ),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) { 
                                NormalText(text = "ضمان وصول", color = Color.White)
                                AppSpacer(width = medium)
                                Image(
                                    painter = painterResource(id = R.drawable.ic_box),
                                    contentDescription = null,
                                    modifier = Modifier.size(24.dp)
                                )
                            }

                            AppSpacer(width = medium)

                            Row(
                                modifier = Modifier
                                    .weight(1f)
                                    .height(48.dp)
                                    .clickable {
                                        if (!viewModel.userFinishedOnBoarding()) {
                                            showBottomSheet = true
                                        }
                                    }
                                    .background(
                                        color = buttonColor,
                                        shape = RoundedCornerShape(spacing)
                                    ),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                NormalText(text = "ضمان دفع", color = Color.White)
                                AppSpacer(width = medium)
                                Image(
                                    painter = painterResource(id = R.drawable.ic_cash),
                                    contentDescription = null,
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                        }

                        AppSpacer(height = large)
                    }
                }

                AppSpacer(height = large)

                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    color = Color.White,
                    shadowElevation = 4.dp,
                    shape = RoundedCornerShape(large)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(large),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {

                        Image(
                            painter = painterResource(id = R.drawable.wallet_image),
                            contentDescription = null,
                            modifier = Modifier.size(45.dp)
                        )

                        AppSpacer(width = large)

                        Column {
                            NormalText(text = "الرصيد المتاح في محفظتك", fontSize = 14)
                            AppSpacer(height = medium)
                            HeaderText(text = "500 EGP", fontSize = 18)
                            AppSpacer(height = medium)

                            Row(
                                modifier = Modifier
                                    .height(32.dp)
                                    .background(
                                        color = lightGrayColor,
                                        shape = RoundedCornerShape(large)
                                    )
                                    .padding(horizontal = spacing, vertical = 4.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                NormalText(text = "+201155487795", color = Color.Gray)
                                AppSpacer(width = medium)
                                Image(
                                    painter = painterResource(id = R.drawable.ic_check),
                                    contentDescription = null,
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                        }
                    }
                }

            }
        }

        // Show the Bottom Sheet
        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = { showBottomSheet = false },
                sheetState = sheetState,
                containerColor = Color.White,
            ) {
                BottomSheetView(viewModel = viewModel)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetView(
    viewModel: HomeViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = spacing, end = spacing, top = large, bottom = large)

    ) {

        LoadingView(viewModel.isLoadingProgressBar.collectAsState(false).value)

        var email by remember { mutableStateOf("") }
        var name by remember { mutableStateOf("") }
        var idNumber by remember { mutableStateOf("") }
        var phone by remember { mutableStateOf("") }

        val user by remember { viewModel.user }

        Image(
            painter = painterResource(id = R.drawable.logo_1_1),
            contentDescription = null,
            modifier = Modifier
                .size(50.dp)
        )

        AppSpacer(height = 24.dp)

        HeaderText(text = "ضمان خدمتك يبدأ باستكمال بياناتك", fontSize = 16)
        AppSpacer(height = large)
        NormalText(text = "قم باستكمال بيانات حسابك لتجربة أسرع و أسهل .", fontSize = 14)

        AppSpacer(height = spacing)

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            NormalText(text = "رقم هاتفك هو", fontSize = 14)

            HeaderText(text = "تعديل", fontSize = 14, color = SkyColor)

        }

        AppSpacer(height = large)

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            NormalText(
                text = user.phone, fontSize = 14,
                modifier = Modifier.background(
                    color = borderColor, shape = RoundedCornerShape(
                        large
                    )
                )
            )

            AppSpacer(width = medium)
            Image(
                painter = painterResource(id = R.drawable.ic_check),
                contentDescription = null,
                modifier = Modifier.size(18.dp)
            )
        }

        AppSpacer(width = spacing)

        MainEditText(
            text = name,
            label = if (user.name == null)  "الاسم" else user.name!!,
            onTextChange = {
                name = it
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(76.dp),
            shape = RoundedCornerShape(spacing),
            isError = false
        )

        AppSpacer(height = large)

        MainEditText(
            text = email,
            label = if (user.email == null)"البريد الالكتروني" else user.email!!,
            onTextChange = {
                email = it
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(76.dp),
            shape = RoundedCornerShape(spacing),
            isError = false
        )

        AppSpacer(height = large)

        MainEditText(
            text = idNumber,
            label = if (user.id == null) "رقم البطاقة" else user.id.toString(),
            onTextChange = {
                idNumber = it
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(76.dp),
            shape = RoundedCornerShape(spacing),
            isError = false
        )

        AppSpacer(height = spacing)

        MainButton(text = "ابدأ طلبك") {
            viewModel.completeUserData(
                CompleteUserModel(
                name = name,
                email = email,
                idNumber = idNumber,
                device = "android"
                )
            )
        }

        AppSpacer(height = spacing)

        NormalText(
            text = "ليس الآن",
            modifier = Modifier
                .padding(horizontal = spacing)
                .align(Alignment.CenterHorizontally),
            fontSize = 16,
            color = SkyColor
        )

    }


}