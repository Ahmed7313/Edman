package com.daman.edman.screens.Wallet.ChargeWallet

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.aramex.mypos.Presentation.Components.MainEditTextWithoutIcon
import com.daman.edman.R
import com.daman.edman.screens.Wallet.WalletViewModel
import com.daman.edman.screens.components.AppSpacer
import com.daman.edman.screens.components.AppSpacerHeight
import com.daman.edman.screens.components.BorderView
import com.daman.edman.screens.components.HeaderText
import com.daman.edman.screens.components.IconTextView
import com.daman.edman.screens.components.NormalText
import com.daman.edman.screens.components.ToolBarView
import com.daman.edman.ui.theme.SkyColorBlue
import com.daman.edman.ui.theme.buttonColor
import com.daman.edman.ui.theme.grayColor
import com.trend.camelx.ui.theme.large
import com.trend.camelx.ui.theme.medium
import com.trend.camelx.ui.theme.spacing
import com.trend.thecontent.screens.components.MainButton

@Composable
fun ChargeWalletScreen(
    navHostController: NavHostController,
    viewModel: WalletViewModel = hiltViewModel()
) {
    val instapayAccounts by viewModel.instapayAccounts
    val rechargeState    by viewModel.rechargeState

    // ── Navigate back after successful recharge ───────────────────────────────
    LaunchedEffect(Unit) {
        viewModel.navigateBack.collect { shouldNavigate ->
            if (shouldNavigate) navHostController.popBackStack()
        }
    }

    // ── Local form state ──────────────────────────────────────────────────────
    var amount        by remember { mutableStateOf("") }
    var amountError   by remember { mutableStateOf(false) }
    var screenshotUri by remember { mutableStateOf<Uri?>(null) }

    // Selected instapay account
    var selectedAccountId    by remember { mutableStateOf<Int?>(null) }
    var selectedAccountLabel by remember { mutableStateOf("اختر حساب InstaPay") }
    var accountMenuExpanded  by remember { mutableStateOf(false) }

    // Auto-select first account when loaded
    LaunchedEffect(instapayAccounts) {
        if (instapayAccounts.isNotEmpty() && selectedAccountId == null) {
            val first = instapayAccounts.first()
            selectedAccountId    = first.id
            selectedAccountLabel = "${first.name} - ${first.accountNumber}"
        }
    }

    // ── Gallery launcher for screenshot ──────────────────────────────────────
    val screenshotLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        screenshotUri = uri
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        ToolBarView(
            stringResource(R.string.add_balance),
            navHostController = navHostController
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(start = large, end = large, top = large, bottom = large)
        ) {
            Image(
                painter            = painterResource(id = R.drawable.logo_1_1),
                contentDescription = null,
                modifier           = Modifier.size(50.dp)
            )

            AppSpacer(height = 24.dp)

            HeaderText(
                text     = stringResource(R.string.add_balance_in_edman),
                fontSize = 16
            )
            AppSpacer(height = large)
            NormalText(
                text     = stringResource(R.string.add_balance_details),
                fontSize = 14
            )

            AppSpacer(height = spacing)

            // ── Instapay account selector ─────────────────────────────────────
            IconTextView(
                icon = R.drawable.ic_money_black,
                text = "حساب الإستلام"
            )

            AppSpacerHeight()

            Box {
                BorderView(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { accountMenuExpanded = true }
                ) {
                    Row(
                        modifier            = Modifier.fillMaxWidth(),
                        verticalAlignment   = Alignment.CenterVertically
                    ) {
                        NormalText(
                            text     = selectedAccountLabel,
                            fontSize = 14,
                            modifier = Modifier.weight(1f)
                        )
                        Icon(
                            painter            = painterResource(R.drawable.ic_wonder_mark),
                            contentDescription = null,
                            modifier           = Modifier.size(20.dp),
                            tint               = grayColor
                        )
                    }
                }

                DropdownMenu(
                    expanded        = accountMenuExpanded,
                    onDismissRequest = { accountMenuExpanded = false }
                ) {
                    instapayAccounts.forEach { account ->
                        DropdownMenuItem(
                            text = {
                                NormalText(
                                    text = "${account.name} - ${account.accountNumber}",
                                    fontSize = 14
                                )
                            },
                            onClick = {
                                selectedAccountId    = account.id
                                selectedAccountLabel = "${account.name} - ${account.accountNumber}"
                                accountMenuExpanded  = false
                            }
                        )
                    }
                }
            }

            AppSpacer(height = spacing)

            // ── Amount input ──────────────────────────────────────────────────
            IconTextView(
                icon = R.drawable.ic_money_black,
                text = stringResource(R.string.balance)
            )

            AppSpacerHeight()

            BorderView {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    NormalText(text = stringResource(R.string.balance), fontSize = 12)
                    AppSpacer(width = medium)
                    Icon(
                        painter            = painterResource(R.drawable.ic_wonder_mark),
                        contentDescription = null,
                        modifier           = Modifier.size(20.dp),
                        tint               = grayColor
                    )
                }

                AppSpacerHeight()

                Row(verticalAlignment = Alignment.CenterVertically) {
                    MainEditTextWithoutIcon(
                        text         = amount,
                        onTextChange = {
                            amount      = it
                            amountError = false
                        },
                        isError      = amountError,
                        eraseBorder  = true,
                        label        = "0.00",
                        modifier     = Modifier
                            .wrapContentWidth()
                            .width(150.dp)
                    )

                    HeaderText(
                        text     = "EGP",
                        fontSize = 12,
                        modifier = Modifier
                            .weight(1f)
                            .padding(top = 12.dp)
                    )
                }
            }

            AppSpacer(height = spacing)

            // ── Screenshot picker ─────────────────────────────────────────────
            IconTextView(
                icon = R.drawable.ic_upload,
                text = "لقطة شاشة الدفع"
            )

            AppSpacerHeight()

            BorderView(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { screenshotLauncher.launch("image/*") }
            ) {
                Row(
                    modifier          = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter            = painterResource(R.drawable.ic_upload),
                        contentDescription = null,
                        modifier           = Modifier.size(20.dp),
                        tint               = SkyColorBlue
                    )
                    AppSpacer(width = medium)
                    NormalText(
                        text     = if (screenshotUri != null) "✓ تم اختيار الصورة" else "اضغط لرفع لقطة الشاشة",
                        fontSize = 14,
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            AppSpacer(height = 90.dp)

            // ── Loading indicator ─────────────────────────────────────────────
            if (rechargeState.isLoading) {
                CircularProgressIndicator(
                    color    = SkyColorBlue,
                    modifier = Modifier
                        .size(40.dp)
                        .align(Alignment.CenterHorizontally)
                )
                AppSpacerHeight()
            }

            // ── Submit button ─────────────────────────────────────────────────
            MainButton(
                text    = stringResource(R.string.add_balance_now),
                enabled = !rechargeState.isLoading
            ) {
                // Validate
                if (amount.isBlank() || amount.toDoubleOrNull() == null) {
                    amountError = true
                    return@MainButton
                }
                val accountId = selectedAccountId
                if (accountId == null) return@MainButton

                viewModel.rechargeWallet(
                    instapayAccountId = accountId,
                    amount            = amount,
                    screenshotUri     = screenshotUri
                )
            }

            AppSpacer(height = spacing)

            HeaderText(
                text     = stringResource(R.string.cancel),
                fontSize = 16,
                color    = buttonColor,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .clickable { navHostController.popBackStack() }
            )
        }
    }
}