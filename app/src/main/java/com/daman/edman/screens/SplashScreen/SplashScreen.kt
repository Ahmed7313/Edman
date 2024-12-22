package com.daman.edman.screens.SplashScreen

import android.util.Log
import android.window.SplashScreen
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.aramex.mypos.Presentation.NavGrapghs.ContainerScreen
import com.aramex.mypos.Presentation.NavGrapghs.LoginScreen
import com.daman.edman.R
import com.daman.edman.screens.Auth.Login.LoginScreen
import com.daman.edman.screens.components.AppHolder
import com.trend.camelx.ui.theme.spacing
import com.trend.thecontent.data.local.preference.SavePreferences
import com.trend.thecontent.data.local.preference.SavePreferences.Companion.signedInState
import kotlinx.coroutines.delay
import timber.log.Timber

@Composable
fun SplashScreen(
    navController: NavController
) {

    val context = LocalContext.current

    val preferences = SavePreferences(context)

    LaunchedEffect(key1 = true){
        delay(2000)
        if (preferences.getSignedInState() || !preferences.getToken().isEmpty()){
            Timber.tag("splashScreen").v(signedInState.toString())

            navController.navigate(ContainerScreen){
                popUpTo(com.aramex.mypos.Presentation.NavGrapghs.SplashScreen) {
                    saveState = true
                    inclusive = true
                }
            }

        }else{
            navController.navigate(LoginScreen){
                popUpTo(com.aramex.mypos.Presentation.NavGrapghs.SplashScreen) {
                    saveState = true
                    inclusive = true
                }
            }
        }
    }

    AppHolder() {
        Box(modifier = Modifier.fillMaxSize()) {

            Image(
                painter = painterResource(id = R.drawable.logo_1_1),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp)
                    .padding(spacing)
                    .align(Alignment.Center)
            )
        }
    }
}