package com.aramex.mypos.Presentation.NavGrapghs

import kotlinx.serialization.Serializable

@Serializable
object LoginScreen

@Serializable
data class CodeScreen(
    val phone : String
)


@Serializable
object ContainerScreen

@Serializable
object HomeScreen

@Serializable
object SplashScreen

@Serializable
object RequestScreen

@Serializable
object RequestGuaranteeDetailsScreen

@Serializable
object CreateRequestScreen


@Serializable
object OrderDetailsScreen

@Serializable
object ChargeWalletScreen

@Serializable
object UserProfileScreen

@Serializable
object AddCardScreen

@Serializable
object CreditCardsScreen

@Serializable
object NotificationScreen
