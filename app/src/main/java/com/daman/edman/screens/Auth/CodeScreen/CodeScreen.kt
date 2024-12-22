package com.daman.edman.screens.Auth.CodeScreen

import android.os.Build
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.aramex.mypos.Presentation.Components.MainEditText
import com.aramex.mypos.Presentation.Components.MainEditTextWithoutIcon
import com.aramex.mypos.Presentation.NavGrapghs.ContainerScreen
import com.aramex.mypos.Presentation.NavGrapghs.HomeScreen
import com.daman.edman.R
import com.daman.edman.screens.components.AppSpacer
import com.daman.edman.screens.components.HeaderText
import com.daman.edman.screens.components.NormalText
import com.daman.edman.screens.components.SmsRetrieverUserConsent
import com.daman.edman.ui.theme.SkyColor
import com.trend.camelx.ui.theme.large
import com.trend.camelx.ui.theme.spacing
import com.trend.thecontent.data.local.preference.SavePreferences
import com.trend.thecontent.screens.components.LoadingView
import com.trend.thecontent.screens.components.MainButton
import kotlinx.coroutines.delay
import timber.log.Timber
import kotlin.time.Duration.Companion.seconds

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
 fun CodeScreen(
    phone: String,
    navController: NavController,
    viewModel: ConfirmCodeViewModel = hiltViewModel()
 ) {

    val context = LocalContext.current

    var isLoading by remember { mutableStateOf(false) }

    var startAction by remember { mutableStateOf(false) }

    val focusManager = LocalFocusManager.current
    var (digit1, setDigit1) = remember {
        mutableStateOf("")
    }
    var (digit2, setDigit2) = remember {
        mutableStateOf("")
    }
    var (digit3, setDigit3) = remember {
        mutableStateOf("")
    }
    var (digit4, setDigit4) = remember {
        mutableStateOf("")
    }

    var (digit5, setDigit5) = remember {
        mutableStateOf("")
    }

    var sendCode by remember {
        mutableStateOf(true)
    }

    var loginSmsRetrieverUserConsentCheck by remember {
        mutableStateOf(true)
    }

    var loginImmediatly by remember {
        mutableStateOf(0)
    }
    val focusRequester = FocusRequester()

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    SmsRetrieverUserConsent { _, code ->
        isLoading = true
        digit1 = code[0].toString()
        digit2 = code[1].toString()
        digit3 = code[2].toString()
        digit4 = code[3].toString()
        digit5 = code[4].toString()
        setDigit1(digit1)
        setDigit2(digit2)
        setDigit3(digit3)
        setDigit4(digit4)
        setDigit5(digit5)
        loginSmsRetrieverUserConsentCheck = true
        //viewModel.checkPin(phone = phone, pin = code)
        startAction =  true
        Timber.tag("Check the code").v(digit1 + digit2 + digit3 + digit4, digit5)
    }


    var resendCodeStateClicked by remember { mutableStateOf(true) }
    var timer by remember { mutableStateOf(60) }

    LaunchedEffect(
        key1 = digit1,
    ) {
        if (digit1.isNotEmpty()) {
            focusManager.moveFocus(
                focusDirection = FocusDirection.Next,
            )
        }
    }
    LaunchedEffect(
        key1 = digit2,
    ) {
        if (digit2.isNotEmpty()) {
            focusManager.moveFocus(
                focusDirection = FocusDirection.Next,
            )
        }
    }
    LaunchedEffect(
        key1 = digit3,
    ) {
        if (digit3.isNotEmpty()) {
            focusManager.moveFocus(
                focusDirection = FocusDirection.Next,
            )
        }
    }

    LaunchedEffect(
        key1 = digit4,
    ) {
        if (digit4.isNotEmpty()) {
            focusManager.moveFocus(
                focusDirection = FocusDirection.Next,
            )
        }
    }

    var otp by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .background(color = Color.White)
            .fillMaxSize()
            .padding(start = spacing, end = spacing, top = large, bottom = large),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth(),
        )
        {
            Image(
                painter = painterResource(id = R.drawable.ic_back_arrow_black),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .clickable {
                        //navigator.popBackStack()
                    }
                    .rotate(if (LocalContext.current.resources.configuration.layoutDirection == View.LAYOUT_DIRECTION_LTR) 0f else 180f)
            )
        }


        Image(
            painter = painterResource(id = R.drawable.lock),
            contentDescription = null,
            modifier = Modifier
                .padding(top = 120.dp)
                .fillMaxWidth()
        )

        AppSpacer(height = 32.dp)

        HeaderText(
            text = "رمز التحقق من هويتك",
            fontSize = 24,
        )

        AppSpacer(height = large)

        NormalText(text = "قم بإدخال رمز التحقق المكون من 5 أرقام المرسل إلى رقم الهاتف المسجل.")

        AppSpacer(height = large)


        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            )
            {
                var textValue1 by remember { mutableStateOf("") }
                var textValue2 by remember { mutableStateOf("") }
                var textValue3 by remember { mutableStateOf("") }
                var textValue4 by remember { mutableStateOf("") }

                val maxChar = 1

                MainEditTextWithoutIcon(
                    text = digit1,
                    onTextChange = { if (it.length <= maxChar) setDigit1(it) },
                    isError = false,
                    modifier = Modifier
                        .width(60.dp)
                        .focusRequester(focusRequester),
                    keyboardType = KeyboardOptions(
                        keyboardType = KeyboardType.Phone,
                        imeAction = ImeAction.Next
                    ),
                    label = "",
                    textAlign = true // Explicitly set textAlign to true
                )
                MainEditTextWithoutIcon(
                    text = digit2,
                    onTextChange = { if (it.length <= maxChar) setDigit2(it) },
                    isError = false,
                    modifier = Modifier.width(60.dp),
                    keyboardType = KeyboardOptions(
                        keyboardType = KeyboardType.Phone,
                        imeAction = ImeAction.Next
                    ),
                    label = "",
                    textAlign = true // Explicitly set textAlign to true
                )
                MainEditTextWithoutIcon(
                    text = digit3,
                    onTextChange = { if (it.length <= maxChar) setDigit3(it) },
                    isError = false,
                    modifier = Modifier.width(60.dp),
                    keyboardType = KeyboardOptions(
                        keyboardType = KeyboardType.Phone,
                        imeAction = ImeAction.Next
                    ),
                    label = "",
                    textAlign = true // Explicitly set textAlign to true
                )
                MainEditTextWithoutIcon(
                    text = digit4,
                    onTextChange = { if (it.length <= maxChar) setDigit4(it) },
                    isError = false,
                    modifier = Modifier.width(60.dp),
                    keyboardType = KeyboardOptions(
                        keyboardType = KeyboardType.Phone,
                        imeAction = ImeAction.Next
                    ),
                    label = "",
                    textAlign = true // Explicitly set textAlign to true
                )
                MainEditTextWithoutIcon(
                    text = digit5,
                    onTextChange = { if (it.length <= maxChar) setDigit5(it) },
                    isError = false,
                    modifier = Modifier.width(60.dp),
                    keyboardType = KeyboardOptions(
                        keyboardType = KeyboardType.Phone,
                        imeAction = ImeAction.Next
                    ),
                    label = "",
                    textAlign = true // Explicitly set textAlign to true
                )

                otp = "${digit1}${digit2}${digit3}${digit4}${digit5}"
                Timber.tag("optText").e(otp)

            }
        }
        if ( digit1.isNotEmpty() && digit2.isNotEmpty() && digit3.isNotEmpty() && digit4.isNotEmpty() && digit5.isNotEmpty()) {
            //isLoading = true
            startAction = true
            loginImmediatly = 1
        }

        Text(
            text = "اعادة الارسال",
            fontSize = 18.sp,
            modifier = Modifier
                .padding(top = 42.dp, start = 24.dp, end = 24.dp)
                .clickable(
                    enabled = resendCodeStateClicked == false,
                ) {
                    resendCodeStateClicked = true
                    timer = 60

                    //viewModel.resendOTP(phone)
                },
            color = if (!resendCodeStateClicked) SkyColor else Color.Gray
        )
//        Text(text = "00:$timer",
//            modifier = Modifier.padding(top = 24.dp, start = 24.dp, end = 24.dp),
//            fontSize = 18.sp,
//            color = OrangeTextColor,
//            fontWeight = FontWeight.Bold)

        if (resendCodeStateClicked) {
            Text(text = "00:$timer",
                modifier = Modifier.padding(top = 24.dp, start = 24.dp, end = 24.dp),
                fontSize = 18.sp,
                color = SkyColor,
                fontWeight = FontWeight.Bold)
        }


        MainButton(text = stringResource(R.string.Continue), modifier = Modifier.padding(top = 48.dp, start = 24.dp, end = 24.dp)) {
            viewModel.confirmCode(ConfirmCodeViewModel.ConfirmCodeModel(phone,"$digit1$digit2$digit3$digit4$digit5"))
        }

        if (isLoading && startAction) {
            LoadingView(isLoading =isLoading)
        }

        LaunchedEffect(key1 = viewModel.msg.value) {
            if (viewModel.msg.value.isNotEmpty()) {
                Toast.makeText(context, viewModel.msg.value, Toast.LENGTH_SHORT).show()
                viewModel.clearErrorMessage() // Clear the error message after displaying it
            }
        }


        LaunchedEffect(key1 = viewModel.navigate) {
            viewModel.navigate.collect{
                if(it){
                    navController.navigate(ContainerScreen)
                }
            }
        }

        LaunchedEffect(key1 = loginImmediatly){
            if (loginImmediatly == 1 && !loginSmsRetrieverUserConsentCheck){
               // viewModel.checkPin(phone, pin = otp)
                loginImmediatly = 2
            }
        }


        LaunchedEffect(resendCodeStateClicked == true) {
            while(resendCodeStateClicked) {
                delay(1.seconds)
                timer--
                if (timer == 0) {
                    resendCodeStateClicked = false
                    timer = 60
                    break
                }
            }
        }

    }
}