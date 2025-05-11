package com.daman.edman.screens.Home.GarantieRequest

import android.net.Uri
import android.webkit.URLUtil
import ImagePicker
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.aramex.mypos.Common.showErrorMsg
import com.aramex.mypos.Presentation.Components.MainEditTextFramed
import com.daman.edman.R
import com.daman.edman.screens.components.*
import com.daman.edman.ui.theme.buttonColor
import com.trend.camelx.ui.theme.large
import com.trend.camelx.ui.theme.spacing
import com.trend.thecontent.screens.components.LoadingViewFullScreen
import com.trend.thecontent.screens.components.MainButton
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateRequestScreen(
    navHostController: NavHostController,
    viewModel: CreateRequestViewModel = hiltViewModel()
) {
    // 1) collect your ViewModel flows
    val uiState   by viewModel.state.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState(initial = false)

    // 2) local state holders for every input field:
    var productDiscreption    by remember { mutableStateOf("") }
    var orderNumber          by remember { mutableStateOf("") }
    var price                by remember { mutableStateOf("") }
    var productLink          by remember { mutableStateOf("") }
    var pageLink             by remember { mutableStateOf("") }

    val stateOfproducts      = listOf("مستعمل", "جديد")
    var selectedStatus       by remember { mutableStateOf(stateOfproducts.first()) }

    val deliveryOptions      = listOf("3 أيام", "بعد يومان", "بعد يوم", "اليوم")
    var selectedDelivery     by remember { mutableStateOf(deliveryOptions.first()) }
    var customDeliveryDate   by remember { mutableStateOf<String?>(null) }

    var productImageUri      by remember { mutableStateOf<Uri?>(null) }
    var billOfLadingImageUri by remember { mutableStateOf<Uri?>(null) }
    var receiptBillImageUri  by remember { mutableStateOf<Uri?>(null) }

    var shippingConfirmed    by remember { mutableStateOf(false) }

    // for showing errors as toasts:
    val ctx = LocalContext.current

    // bottom‐sheet and scope
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val scope      = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }

    // when creation succeeds, show bottom sheet
    LaunchedEffect(uiState.isSuccess) {
        if (uiState.isSuccess) {
            showBottomSheet = true
            scope.launch { sheetState.show() }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        ToolBarView("طلب ضمان", navHostController = navHostController)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = spacing, end = spacing, top = large, bottom = large)
                .verticalScroll(rememberScrollState())
        ) {

            IconTextView(text = "عن المنتج", icon = R.drawable.ic_black_box)
            AppSpacer(height = large)

            MainEditTextFramed(
                text = productDiscreption,
                onTextChange = { productDiscreption = it },
                isError = false,
                label = "وصف المنتج",
                aboveText = "المنتج",
            )
            AppSpacer(height = large)

            MainEditTextFramed(
                text = orderNumber,
                onTextChange = { orderNumber = it },
                isError = false,
                label = "أدخل رقم طلب مشترياتك هنا",
                aboveText = "رقم الطلب",
            )
            AppSpacer(height = large)

            MainEditTextFramed(
                text = price,
                onTextChange = { price = it },
                isError = false,
                label = "0.00 ",
                aboveText = "المبلغ",
                trailingIcon = {
                    HeaderText(text = "EGP", fontSize = 14, modifier = Modifier.padding(end = 8.dp))
                }
            )
            AppSpacer(height = large)

            NormalText(text = "حالة المنتج", fontSize = 16)
            AppSpacer(height = large)
            SegmentedPills(
                options = stateOfproducts,
                onOptionSelected = { selectedStatus = it }
            )
            AppSpacer(height = large)

            NormalText(text = "مدة التوصيل", fontSize = 16)
            AppSpacer(height = large)
            SegmentedPills(
                options = deliveryOptions,
                onOptionSelected = { selectedDelivery = it }
            )
            AppSpacer(height = large)

            NormalText(text = "أو", modifier = Modifier.align(Alignment.CenterHorizontally))
            AppSpacer(height = large)

            DatePickerView { customDeliveryDate = it }
            AppSpacerHeight()

            IconTextView(text = "مشاركة روابط", icon = R.drawable.ic_link_sharing)
            AppSpacerHeight()

            MainEditTextFramed(
                text = productLink,
                onTextChange = { productLink = it },
                isError = false,
                label = "يمكنك مشاركة رابط طلب منتجك هنا",
                aboveText = "رابط طلب منتجك",
            )
            AppSpacerHeight()

            MainEditTextFramed(
                text = pageLink,
                onTextChange = { pageLink = it },
                isError = false,
                label = "رابط الصفحة الرسمية",
                aboveText = "رابط الصفحة الرسمية للبائع",
            )
            AppSpacerHeight()

            IconTextView(text = "مشاركة صور الطلب", icon = R.drawable.ic_camera)
            AppSpacerHeight()

            ImagePicker(
                icon = R.drawable.ic_product_image,
                text = "صورة للمنتج",
                secText = "قم تحميل صورة منتجك هنا"
            ) { uri -> productImageUri = uri }
            AppSpacerHeight()

            ImagePicker(
                icon = R.drawable.ic_product_order,
                text = "صورة لطلب المنتج ",
                secText = "قم تحميل صورة لطلب المنتج هنا"
            ) { uri -> billOfLadingImageUri = uri }
            AppSpacerHeight()

            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = shippingConfirmed,
                    onCheckedChange = { shippingConfirmed = it }
                )
                HeaderText(
                    text = "باختيار هذه الخاصية ...",
                    modifier = Modifier.padding(start = 8.dp),
                    color = Color.Gray,
                    fontSize = 14
                )
            }
            AppSpacerHeight()

            ImagePicker(
                icon = R.drawable.ic_blue_check,
                text = "التأكيد بصورة لبوليصة الشحن ",
                secText = "يرجى تأكيد الشحن ..."
            ) { uri -> receiptBillImageUri = uri }
            AppSpacerHeight()

            MainButton(text = "المتابعة و الدفع") {
                // 1) sequential validation with toasts
                when {
                    productDiscreption.isBlank() -> {
                        ctx.showErrorMsg("يرجى إدخال وصف المنتج")
                    }
                    orderNumber.isBlank() -> {
                        ctx.showErrorMsg("يرجى إدخال رقم الطلب")
                    }
                    price.isBlank() || price.toDoubleOrNull() == null -> {
                        ctx.showErrorMsg("يرجى إدخال مبلغ صالح")
                    }
                    productLink.isBlank() || !URLUtil.isValidUrl(productLink) -> {
                        ctx.showErrorMsg("يرجى إدخال رابط طلب منتج صالح")
                    }
                    pageLink.isBlank() || !URLUtil.isValidUrl(pageLink) -> {
                        ctx.showErrorMsg("يرجى إدخال رابط الصفحة الرسمية صالح")
                    }
                    productImageUri == null -> {
                        ctx.showErrorMsg("يرجى تحميل صورة المنتج")
                    }
                    billOfLadingImageUri == null -> {
                        ctx.showErrorMsg("يرجى تحميل صورة لطلب المنتج")
                    }
                    !shippingConfirmed -> {
                        ctx.showErrorMsg("يرجى تأكيد شحن الطلب")
                    }
                    receiptBillImageUri == null -> {
                        ctx.showErrorMsg("يرجى تحميل بوليصة الشحن")
                    }
                    else -> {
                        // 2) all good → API call
                        viewModel.createOrder(
                            sellerId             = 1,
                            productDescription   = productDiscreption,
                            productStatus        = stateOfproducts.indexOf(selectedStatus),
                            deliveryTime         = customDeliveryDate ?: selectedDelivery,
                            price                = price,
                            orderNumber          = orderNumber,
                            productOrderUrl      = productLink,
                            officialPageUrl      = pageLink,
                            productImageUri      = productImageUri,
                            billOfLadingImageUri = billOfLadingImageUri,
                            receiptBillImageUri  = receiptBillImageUri,
                            shippingConfirmed    = shippingConfirmed
                        )
                    }
                }
            }

            AppSpacer(height = spacing)

            HeaderText(
                text = "إلغاء",
                fontSize = 16,
                color = buttonColor,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            if (showBottomSheet) {
                ModalBottomSheet(
                    onDismissRequest = { showBottomSheet = false },
                    sheetState = sheetState,
                    containerColor = Color.White,
                ) {
                    // BottomSheetView(...)
                }
            }
        }
    }

    // 3) overlay your existing loading dialog:
    LoadingViewFullScreen(isLoading = isLoading)
}
