package com.daman.edman.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable


import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.aramex.mypos.Presentation.NavGrapghs.SetUpHomeNavGraph
import com.daman.edman.R
import com.daman.edman.screens.Home.HomeScreen
import com.daman.edman.screens.components.HeaderText
import com.daman.edman.ui.theme.ArabotoFont
import com.daman.edman.ui.theme.RedColor
import compose.material.theme.bottomnav.BottomBarScreen

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun ContainerScreen() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomBar(navController = navController)
        }
    ) {
        Modifier.padding(it)

        SetUpHomeNavGraph(navController = navController)
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.MyOrders,
        BottomBarScreen.Wallet,
        BottomBarScreen.Profile
    )

//    val screenDetails = listOf(
//        Screen.AllCampaignsScreen,
//        Screen.CampaignsScreen,
//        Screen.CompletedCampaignsScreen
//    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val bottomBarDestination = screens.any { it.route == currentDestination?.route }
    //     || screenDetails.any { it.route == currentDestination?.route }

    if (bottomBarDestination) {
        Row(
            modifier = Modifier
                .height(90.dp)
                .fillMaxWidth()
                .background(color = Color.White),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            screens.forEach { screen ->
                AddItem(
                    screen = screen,
                    currentDestination = currentDestination,
                    navController = navController
                )
            }
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    val selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true
    val contentColor = if (selected) Color.Red else Color.Black

    Column(modifier = Modifier
        .weight(1f)
        .clickable(onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = if (selected) screen.icon_focused  else screen.icon),
            contentDescription = "icon",
            //  tint = contentColor
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(text = stringResource(id = screen.title),
            color = if (selected) RedColor else Color.Gray,
            fontFamily = ArabotoFont,
            fontSize = 12.sp,)
    }
}