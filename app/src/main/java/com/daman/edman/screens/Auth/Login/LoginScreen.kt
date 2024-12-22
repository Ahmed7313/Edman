package com.daman.edman.screens.Auth.Login

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.aramex.mypos.Presentation.Components.MainEditText
import com.aramex.mypos.Presentation.NavGrapghs.CodeScreen
import com.daman.edman.R
import com.daman.edman.screens.components.AppHolder
import com.daman.edman.screens.components.AppSpacer
import com.daman.edman.screens.components.HeaderText
import com.daman.edman.screens.components.NormalText
import com.daman.edman.ui.theme.borderColor
import com.trend.camelx.ui.theme.extraMedium
import com.trend.camelx.ui.theme.extraSmall
import com.trend.camelx.ui.theme.large
import com.trend.camelx.ui.theme.spacing
import com.trend.thecontent.screens.components.LoadingView
import com.trend.thecontent.screens.components.MainButton


@Composable
 fun LoginScreen(
    navController : NavController,
    viewModel: LoginViewModel = hiltViewModel()
) {

    AppHolder {

        LoadingView(viewModel.isLoadingProgressBar.collectAsState(initial = false).value)


        AppSpacer(height = 80.dp)

        Image(
            painter = painterResource(id = R.drawable.logo_1_1),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
        )

        AppSpacer(height = 34.dp)

        HeaderText(text = "تسجيل الدخول", fontSize = 24)

        AppSpacer(height = large)

        NormalText(text = "قم بتسجيل الدخول عن طريق رقم الهاتف او من خلال بريدك الإلكتروني.")

        AppSpacer(height = large)

        var phone by remember { mutableStateOf("") }
        var phoneError by remember { mutableStateOf(false) }

        MainEditText(
            text = phone,
            onTextChange = { phone = it },
            isError = phoneError,
            label = "رقم الهاتف",
            keyboardType = KeyboardOptions(keyboardType = KeyboardType.Phone),
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(spacing),

        )

        AppSpacer(height = large)

        MainButton(text = "التالي") {
            if(phone.isNotEmpty()) {
                phoneError = false
                viewModel.login(LoginViewModel.LoginModel(phone))
            }
        }

        LaunchedEffect(key1 = viewModel.navigate) {
            viewModel.navigate.collect{
                if(it){
                    navController.navigate(CodeScreen(
                        phone = phone
                    ))
                }
            }
        }

         AppSpacer(height = 20.dp)

        NormalText(text = "أو", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)

        AppSpacer(height = large)

        Surface(
            border = BorderStroke(1.dp, borderColor),
            modifier = Modifier.fillMaxWidth().height(48.dp),
            shadowElevation = 0.dp,
            shape = RoundedCornerShape(spacing)
        ) {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                Row(
                    modifier = Modifier.align(Alignment.Center),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Image(
                        painter = painterResource(id = R.drawable.google_logo),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )

                    AppSpacer(width = extraMedium)

                    HeaderText(
                        text = "المتابعة إلى Gmail",
                        modifier = Modifier,
                        textAlign = TextAlign.Center,
                        fontSize = 16,
                    )

                }
            }
        }
    }
}