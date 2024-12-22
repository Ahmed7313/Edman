package com.aramex.mypos.Presentation.NavGrapghs

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.daman.edman.screens.Auth.Login.LoginScreen
import com.daman.edman.screens.ContainerScreen
import com.daman.edman.screens.Home.HomeScreen
import com.daman.edman.screens.MyOrders.MyOrdersScreen
import com.daman.edman.screens.Profile.ProfileScreen
import com.daman.edman.screens.Wallet.WalletScreen
import compose.material.theme.bottomnav.BottomBarScreen


@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun SetUpHomeNavGraph (navController : NavHostController, modifier: Modifier = Modifier) {

    NavHost(navController = navController, startDestination = BottomBarScreen.Home.route, modifier = modifier) {

        composable(BottomBarScreen.Home.route) {
            HomeScreen()
        }

        composable(BottomBarScreen.MyOrders.route) {
            MyOrdersScreen()
        }

        composable(BottomBarScreen.Wallet.route) {
            WalletScreen()
        }

        composable(BottomBarScreen.Profile.route) {
             ProfileScreen()
        }

        composable<ContainerScreen> {
            ContainerScreen()
        }

        loginNavGraph(navController = navController)


    }
}