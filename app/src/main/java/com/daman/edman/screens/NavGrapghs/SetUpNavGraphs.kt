package com.aramex.mypos.Presentation.NavGrapghs

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.daman.edman.screens.ContainerScreen
import com.daman.edman.screens.SplashScreen.SplashScreen
import com.trend.thecontent.data.local.preference.SavePreferences

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun SetUpNavGraph (navController : NavHostController, modifier: Modifier = Modifier) {

    NavHost(navController = navController, startDestination = SplashScreen, modifier = modifier) {

        loginNavGraph(navController = navController)


        composable<ContainerScreen> {
            ContainerScreen()
        }

        composable<SplashScreen>{
            SplashScreen(navController = navController)
        }
    }
}
