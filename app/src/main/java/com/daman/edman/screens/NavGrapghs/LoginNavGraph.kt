package com.aramex.mypos.Presentation.NavGrapghs

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.daman.edman.screens.Auth.CodeScreen.CodeScreen
import com.daman.edman.screens.Auth.Login.LoginScreen
import com.daman.edman.screens.ContainerScreen

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
fun NavGraphBuilder.loginNavGraph(
    navController: NavHostController
){
    composable<LoginScreen>{
        LoginScreen(navController = navController)
    }

    composable<CodeScreen> {
        val args = it.toRoute<CodeScreen>()
        CodeScreen(navController =  navController, phone = args.phone)
    }

    composable<ContainerScreen> {
        ContainerScreen()
    }

}