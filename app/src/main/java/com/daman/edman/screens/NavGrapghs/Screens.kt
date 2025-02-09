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
object RequestGuaranteeScreen

