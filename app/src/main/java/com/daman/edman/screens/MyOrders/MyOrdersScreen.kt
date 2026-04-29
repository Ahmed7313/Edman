package com.daman.edman.screens.MyOrders

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.aramex.mypos.Presentation.Components.MainEditText
import com.aramex.mypos.Presentation.NavGrapghs.OrderDetailsScreen
import com.daman.edman.R
import com.daman.edman.data.remote.DTO.OrderDTO.Data
import com.daman.edman.screens.components.AppSpacer
import com.daman.edman.screens.components.AppSpacerHeight
import com.daman.edman.screens.components.HeaderText
import com.daman.edman.screens.components.IconTextView
import com.daman.edman.ui.theme.SkyColorBlue
import com.daman.edman.ui.theme.buttonColor
import com.daman.edman.ui.theme.grayColor
import com.trend.camelx.ui.theme.large
import com.trend.camelx.ui.theme.medium
import com.trend.camelx.ui.theme.spacing


// ── Status codes that match the API ──────────────────────────────────────────
private const val STATUS_PENDING     = 1
private const val STATUS_IN_PROGRESS = 2
private const val STATUS_COMPLETED   = 3
private const val STATUS_CANCELLED   = 4

/** Maps an API status int to the string key expected by [OrderItem] */
private fun statusKey(status: Int?): String = when (status) {
    STATUS_PENDING     -> "pending"
    STATUS_IN_PROGRESS -> "progress"
    STATUS_COMPLETED   -> "delivered"
    STATUS_CANCELLED   -> "canceled"
    else               -> "progress"
}

// ─────────────────────────────────────────────────────────────────────────────

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyOrdersScreen(
    navHostController: NavHostController,
    viewModel: MyOrdersViewModel = hiltViewModel()
) {
    val orders  by viewModel.orders
    val state   by viewModel.state
    val selected by viewModel.selectedStatus

    val sheetState    = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }
    var searchQuery   by remember { mutableStateOf("") }

    // ── Derived lists ─────────────────────────────────────────────────────────
    val filteredOrders = if (searchQuery.isBlank()) {
        orders
    } else {
        orders.filter { order ->
            order.product?.contains(searchQuery, ignoreCase = true) == true ||
            order.orderNumber?.contains(searchQuery, ignoreCase = true) == true
        }
    }

    val inProgressOrders = filteredOrders.filter { it.status == STATUS_IN_PROGRESS || it.status == STATUS_PENDING }
    val completedOrders  = filteredOrders.filter { it.status == STATUS_COMPLETED }
    val cancelledOrders  = filteredOrders.filter { it.status == STATUS_CANCELLED }

    // ── Root layout ───────────────────────────────────────────────────────────
    Box(modifier = Modifier.fillMaxSize()) {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = spacing, end = spacing, top = large, bottom = large)
        ) {

            // Header row
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = spacing)
                        .clickable { showBottomSheet = true },
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

                // Search field
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
            }

            // ── In-progress / pending section ─────────────────────────────────
            if (inProgressOrders.isNotEmpty()) {
                item {
                    HeaderText(
                        text = "قيد التنفيذ ( ${inProgressOrders.size} )",
                        color = grayColor,
                        fontSize = 16
                    )
                    AppSpacer(height = large)
                }
                items(inProgressOrders, key = { it.id ?: it.hashCode() }) { order ->
                    OrderItem(
                        statues        = statusKey(order.status),
                        orderedIn      = "تم الطلب في ${order.createdAt ?: "—"}",
                        deliveryDuration = order.deliveryDate ?: "—",
                        description    = buildDescription(order),
                        total          = order.totalPrice ?: order.price ?: "0",
                        onClick        = {
                            navHostController.navigate(OrderDetailsScreen)
                        }
                    )
                    AppSpacer(height = large)
                }
            }

            // ── Completed section ─────────────────────────────────────────────
            if (completedOrders.isNotEmpty()) {
                item {
                    HeaderText(text = "مكتملة", color = grayColor, fontSize = 16)
                    AppSpacer(height = large)
                }
                items(completedOrders, key = { it.id ?: it.hashCode() }) { order ->
                    OrderItem(
                        statues        = statusKey(order.status),
                        orderedIn      = "تم الطلب في ${order.createdAt ?: "—"}",
                        deliveryDuration = order.deliveryDate ?: "—",
                        description    = buildDescription(order),
                        total          = order.totalPrice ?: order.price ?: "0",
                        onClick        = {
                            navHostController.navigate(OrderDetailsScreen)
                        }
                    )
                    AppSpacer(height = large)
                }
            }

            // ── Cancelled section ─────────────────────────────────────────────
            if (cancelledOrders.isNotEmpty()) {
                item {
                    HeaderText(text = "ملغية", color = grayColor, fontSize = 16)
                    AppSpacer(height = large)
                }
                items(cancelledOrders, key = { it.id ?: it.hashCode() }) { order ->
                    OrderItem(
                        statues        = statusKey(order.status),
                        orderedIn      = "تم الطلب في ${order.createdAt ?: "—"}",
                        deliveryDuration = order.deliveryDate ?: "—",
                        description    = buildDescription(order),
                        total          = order.totalPrice ?: order.price ?: "0",
                        onClick        = {
                            navHostController.navigate(OrderDetailsScreen)
                        }
                    )
                    AppSpacer(height = large)
                }
            }

            // ── Empty state ───────────────────────────────────────────────────
            if (!state.isLoading && filteredOrders.isEmpty()) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 48.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        HeaderText(text = "لا توجد طلبات", color = grayColor, fontSize = 16)
                    }
                }
            }

            item { AppSpacer(height = 90.dp) }

            // ── Bottom sheet ──────────────────────────────────────────────────
            item {
                if (showBottomSheet) {
                    ModalBottomSheet(
                        onDismissRequest = { showBottomSheet = false },
                        sheetState = sheetState,
                        containerColor = Color.White,
                    ) {
                        FilterBottomSheetView(
                            currentStatus = selected,
                            onFilterSelected = { status ->
                                showBottomSheet = false
                                viewModel.fetchOrders(status)
                            }
                        )
                    }
                }
            }
        }

        // ── Centered loading indicator ────────────────────────────────────────
        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
                color = SkyColorBlue
            )
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// Helpers
// ─────────────────────────────────────────────────────────────────────────────

private fun buildDescription(order: Data): String {
    val number = order.orderNumber ?: order.id?.toString() ?: "—"
    val product = order.product ?: "—"
    return "طلبك برقم $number لضمان وصول شحنتك ( $product )"
}

// ─────────────────────────────────────────────────────────────────────────────
// Filter Bottom Sheet
// ─────────────────────────────────────────────────────────────────────────────

private data class FilterOption(val label: String, val status: Int?)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterBottomSheetView(
    currentStatus: Int?,
    onFilterSelected: (Int?) -> Unit
) {
    val options = listOf(
        FilterOption("جميع الطلبات",  null),
        FilterOption("قيد التنفيذ",   STATUS_IN_PROGRESS),
        FilterOption("المكتملة",       STATUS_COMPLETED),
        FilterOption("الملغية",        STATUS_CANCELLED),
    )

    Column(modifier = Modifier.selectableGroup()) {
        options.forEach { option ->
            val selected = option.status == currentStatus
            ListItem(
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .selectable(
                        selected = selected,
                        onClick  = { onFilterSelected(option.status) }
                    ),
                headlineContent = {
                    Text(
                        text  = option.label,
                        color = if (selected) Color.Blue else Color.Black
                    )
                },
                trailingContent = {
                    if (selected) {
                        Icon(
                            imageVector     = Icons.Default.Check,
                            contentDescription = "Selected"
                        )
                    }
                }
            )
            if (option != options.last()) Divider()
        }

        AppSpacerHeight()
        HeaderText(
            text     = "إلغاء",
            fontSize = 16,
            color    = buttonColor,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .clickable { onFilterSelected(currentStatus) }
        )
        AppSpacerHeight()
    }
}
