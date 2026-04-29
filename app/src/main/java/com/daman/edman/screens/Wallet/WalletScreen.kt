package com.daman.edman.screens.Wallet

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.aramex.mypos.Presentation.NavGrapghs.ChargeWalletScreen
import com.daman.edman.R
import com.daman.edman.data.remote.DTO.Wallet.Transaction
import com.daman.edman.screens.components.AppSpacer
import com.daman.edman.screens.components.AppSpacerHeight
import com.daman.edman.screens.components.AppToolBar
import com.daman.edman.screens.components.BorderView
import com.daman.edman.screens.components.HeaderText
import com.daman.edman.screens.components.NormalText
import com.daman.edman.ui.theme.SkyColorBlue
import com.daman.edman.ui.theme.lightGrayColor
import com.trend.camelx.ui.theme.large
import com.trend.camelx.ui.theme.medium
import com.trend.thecontent.screens.components.MainButton

@Composable
fun WalletScreen(
    navHostController: NavHostController,
    viewModel: WalletViewModel = hiltViewModel()
) {
    val user             by viewModel.user
    val transactions     by viewModel.transactions
    val transactionsState by viewModel.transactionsState

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = lightGrayColor)
    ) {
        AppToolBar(backView = true) { }

        AppSpacerHeight()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = lightGrayColor)
                .padding(large)
        ) {
            // ── Balance card ──────────────────────────────────────────────────
            BorderView {
                HeaderText(
                    text = stringResource(R.string.available_blanace),
                    fontSize = 12
                )
                AppSpacer(height = large)
                HeaderText(
                    text = "${user.balance ?: "0.0"} EGP",
                    fontSize = 32,
                    color = SkyColorBlue
                )

                AppSpacer(height = large)

                MainButton(text = stringResource(R.string.charge_wallet)) {
                    navHostController.navigate(ChargeWalletScreen)
                }
            }

            AppSpacerHeight()

            NormalText(
                text = stringResource(R.string.previouse_operations),
                fontSize = 16
            )

            // ── Transactions list ─────────────────────────────────────────────
            when {
                transactionsState.isLoading -> {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 32.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(color = SkyColorBlue)
                    }
                }

                transactions.isEmpty() && !transactionsState.isLoading -> {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 32.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        NormalText(text = "لا توجد معاملات سابقة", fontSize = 14)
                    }
                }

                else -> {
                    LazyColumn {
                        items(transactions, key = { it.id ?: it.hashCode() }) { tx ->
                            AppSpacerHeight()
                            WalletItem(
                                headerText = transactionTitle(tx),
                                subText    = transactionSubText(tx)
                            )
                        }
                    }
                }
            }
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// Display helpers
// ─────────────────────────────────────────────────────────────────────────────

private fun transactionTitle(tx: Transaction): String =
    when (tx.type?.lowercase()) {
        "credit" -> "استلام رصيد"
        "debit"  -> "سحب رصيد"
        else     -> tx.description ?: "معاملة"
    }

private fun transactionSubText(tx: Transaction): String {
    val amount = "${tx.amount ?: "0"} EGP"
    val date   = tx.createdAt ?: ""
    return if (date.isNotEmpty()) "$amount · $date" else amount
}

// ─────────────────────────────────────────────────────────────────────────────
// WalletItem (unchanged — kept here to avoid a separate file dependency)
// ─────────────────────────────────────────────────────────────────────────────

@Composable
fun WalletItem(
    modifier: Modifier = Modifier,
    headerText: String,
    subText: String,
) {
    BorderView {
        Row(
            modifier = modifier.fillMaxWidth(),
        ) {
            Image(
                painter            = painterResource(id = R.drawable.ic_dollar_shield),
                contentDescription = null,
                modifier           = Modifier.size(24.dp),
            )
            AppSpacer(width = 8.dp)

            Column {
                HeaderText(text = headerText, fontSize = 16)
                AppSpacer(height = large)
                NormalText(text = subText)
            }
        }
    }
}

@Preview
@Composable
fun WalletItemPreview() {
    WalletItem(
        headerText = "استلام من المشتري",
        subText    = "500 EGP · 2024-01-16"
    )
}