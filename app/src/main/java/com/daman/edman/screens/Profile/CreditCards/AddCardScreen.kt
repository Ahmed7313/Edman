package com.daman.edman.screens.Profile.CreditCards

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.aramex.mypos.Presentation.Components.MainEditTextFramed
import com.daman.edman.R
import com.daman.edman.screens.components.AppSpacer
import com.daman.edman.screens.components.AppSpacerHeight
import com.daman.edman.screens.components.HeaderText
import com.daman.edman.screens.components.NormalText
import com.daman.edman.screens.components.ToolBarView
import com.daman.edman.ui.theme.SkyColorBlue
import com.daman.edman.ui.theme.grayColor
import com.trend.camelx.ui.theme.large
import com.trend.camelx.ui.theme.medium

@Composable
 fun AddCardsScreen(navHostController: NavHostController) {
    var selectedCardType by remember { mutableStateOf(CardType.VISA) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        ToolBarView("إضافة بطاقة",navHostController)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(start = large, end = large, top = large, bottom = large)
        ) {
            HeaderText(text = "بطاقة جديدة", fontSize = 16, color = grayColor)
            AppSpacerHeight()
            NormalText(text = "نوع الحساب")

            CardTypeRow(selectedCardType = selectedCardType) { cardType ->
                selectedCardType = cardType
            }

            selectedCardType.let {
                when (it) {
                    CardType.VISA -> VisaView()
                    CardType.MASTERCARD -> VisaView()
                    CardType.BANK -> BankView()
                    CardType.VODAFONE -> VodafoneView()
                    CardType.INSTAPAY -> InstapayView()
                }
            }
        }
    }
}

@Preview
@Composable
private fun VisaView() {
    Column (modifier = Modifier.fillMaxWidth()){

        var cardNumber by remember { mutableStateOf("") }
        MainEditTextFramed(
            text = cardNumber,
            onTextChange = { cardNumber = it },
            aboveText = "رقم البطاقة البنكية",
            label = "أدخل رقم بطاقتك البنكية"
        )

        AppSpacerHeight()

        Row (modifier = Modifier.fillMaxWidth().height(78.dp)){

            var endDate by remember { mutableStateOf("") }
            MainEditTextFramed(
                modifier = Modifier.weight(1f),
                text = endDate,
                onTextChange = { endDate = it },
                aboveText = "تاريخ الصلاحية",
                label = "MM/YY"
            )

            AppSpacer(width = medium)

            var cvv by remember { mutableStateOf("") }
            MainEditTextFramed(
                modifier = Modifier.weight(1f),
                text = cvv,
                onTextChange = { cvv = it },
                aboveText = "رقم التحقق ",
                label = "أدخل CVV"
            )
        }

        var isChecked = remember { mutableStateOf(false) }

        Row(
            modifier = Modifier,
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
                text = "تفعيل هذه البطاقة لاستخدام سريع و تجربة امنة خلال العمليات. ",
                modifier = Modifier.padding(start = 8.dp),
                color = Color.Gray,
                fontSize = 14
            )
        }
    }
}

@Preview
@Composable
private fun BankView() {
    Column (modifier = Modifier.fillMaxWidth()) {

        var cardNumber by remember { mutableStateOf("") }
        MainEditTextFramed(
            text = cardNumber,
            onTextChange = { cardNumber = it },
            aboveText = "رقم البطاقة البنكية",
            label = "أدخل رقم بطاقتك البنكية"
        )

        AppSpacerHeight()

        var iBan by remember { mutableStateOf("") }
        MainEditTextFramed(
            text = iBan,
            onTextChange = { iBan = it },
            aboveText = "رقم IBAN",
            label = "أدخل رقم  IBAN هنا"
        )
        AppSpacerHeight()

        var swiftCode by remember { mutableStateOf("") }
        MainEditTextFramed(
            text = swiftCode,
            onTextChange = { swiftCode = it },
            aboveText = "رقم SWIFT CODE",
            label = "أدخل رقم SWIFT CODE هنا"
        )
        AppSpacerHeight()

        var isChecked = remember { mutableStateOf(false) }

        Row(
            modifier = Modifier,
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
                text = "تفعيل هذه البطاقة لاستخدام سريع و تجربة امنة خلال العمليات. ",
                modifier = Modifier.padding(start = 8.dp),
                color = Color.Gray,
                fontSize = 14
            )
        }

    }
}

@Preview
@Composable
private fun VodafoneView() {
    Column (modifier = Modifier.fillMaxWidth()) {

        var fullName by remember { mutableStateOf("") }
        MainEditTextFramed(
            text = fullName,
            onTextChange = { fullName = it },
            aboveText = "الاسم بالكامل (اختياري)",
            label = "أدخل الاسم بالكامل هنا"
        )

        AppSpacerHeight()

        var vodafoneCashNumber by remember { mutableStateOf("") }
        MainEditTextFramed(
            text = vodafoneCashNumber,
            onTextChange = { vodafoneCashNumber = it },
            aboveText = "رقم Vodafone cash",
            label = "أدخل رقم  Vodafone cash هنا"
        )

        AppSpacerHeight()
        var isChecked = remember { mutableStateOf(false) }

        Row(
            modifier = Modifier,
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
                text = "تفعيل هذه البطاقة لاستخدام سريع و تجربة امنة خلال العمليات. ",
                modifier = Modifier.padding(start = 8.dp),
                color = Color.Gray,
                fontSize = 14
            )
        }
    }
}

@Preview
@Composable
private fun InstapayView() {
    
}

@Composable
fun CardTypeRow(
    selectedCardType: CardType?,
    onCardTypeSelected: (CardType) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        CardType.values().forEach { cardType ->
            val isSelected = cardType == selectedCardType
            val backgroundColor = if (isSelected) SkyColorBlue.copy(alpha = 0.2f) else Color.Transparent
            val borderColor = if (isSelected) SkyColorBlue else Color.Transparent

            Image(
                painter = painterResource(id = cardType.icon),
                contentDescription = cardType.name,
                modifier = Modifier
                    .size(48.dp)
                    .background(color = backgroundColor, shape = CircleShape)
                    .border(width = 2.dp, color = borderColor, shape = CircleShape)
                    .clickable { onCardTypeSelected(cardType) }
            )
        }
    }
}

enum class CardType(val icon: Int) {
    VISA(R.drawable.visa),
    MASTERCARD(R.drawable.master_card),
    BANK(R.drawable.bank),
    VODAFONE(R.drawable.vodafone),
    INSTAPAY(R.drawable.instapay)
}