package com.aramex.mypos.Presentation.NavGrapghs

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.daman.edman.screens.ContainerScreen
import com.daman.edman.screens.Home.CreateRequest.RequestScreen
import com.daman.edman.screens.Home.GarantieRequest.CreateRequestScreen
import com.daman.edman.screens.Home.HomeScreen
import com.daman.edman.screens.Home.Notifications.NotificationsScreen
import com.daman.edman.screens.MyOrders.MyOrdersScreen
import com.daman.edman.screens.MyOrders.orderdetails.OrderDetailsScreen
import com.daman.edman.screens.Profile.CreditCards.AddCardsScreen
import com.daman.edman.screens.Profile.CreditCards.CreditCardsScreen
import com.daman.edman.screens.Profile.ProfileScreen
import com.daman.edman.screens.Profile.userProfile.UserProfileScreen
import com.daman.edman.screens.Wallet.ChargeWallet.ChargeWalletScreen
import com.daman.edman.screens.Wallet.WalletScreen
import compose.material.theme.bottomnav.BottomBarScreen


@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun SetUpHomeNavGraph (navController : NavHostController, modifier: Modifier = Modifier) {

    NavHost(navController = navController, startDestination = BottomBarScreen.Home.route, modifier = modifier) {

        composable(BottomBarScreen.Home.route) {
            HomeScreen(navController = navController)
        }

        composable(BottomBarScreen.MyOrders.route) {
            MyOrdersScreen(navController)
        }

        composable(BottomBarScreen.Wallet.route) {
            WalletScreen(navController)
        }

        composable(BottomBarScreen.Profile.route) {
             ProfileScreen(navController)
        }

        composable<ContainerScreen> {
            ContainerScreen()
        }

        loginNavGraph(navController = navController)


        composable<RequestScreen> {
            RequestScreen(navController)
        }

        composable<CreateRequestScreen> {
            CreateRequestScreen(navHostController = navController)
        }

        composable<OrderDetailsScreen> {
            OrderDetailsScreen(navController)
        }

        composable<ChargeWalletScreen> {
            ChargeWalletScreen(navHostController = navController)
        }

        composable<UserProfileScreen> {
            UserProfileScreen(navController)
        }

        composable<AddCardScreen> {
            AddCardsScreen(navController)
        }

        composable<CreditCardsScreen> {
            CreditCardsScreen(navController)
        }

        composable<NotificationScreen> {
            NotificationsScreen(navController)
        }
    }
}