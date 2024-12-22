package compose.material.theme.bottomnav

import com.daman.edman.R


sealed class BottomBarScreen(
    val route: String,
    val title: Int,
    val icon: Int,
    val icon_focused: Int
) {

    // for home
    object Home: BottomBarScreen(
        route = "home_screen",
        title = R.string.home,
        icon = R.drawable.ic_home,
        icon_focused = R.drawable.ic_home_selected
    )

    object MyOrders: BottomBarScreen(
        route = "my_orders_screen",
        title = R.string.my_orders,
        icon = R.drawable.ic_orders,
        icon_focused = R.drawable.ic_orders_selected
    )

    object Wallet: BottomBarScreen(
        route = "wallet_screen",
        title = R.string.wallet,
        icon = R.drawable.ic_wallet,
        icon_focused = R.drawable.ic_wallet_selected
    )

    object Profile: BottomBarScreen(
        route = "profile_screen",
        title = R.string.profle,
        icon = R.drawable.ic_profile,
        icon_focused = R.drawable.ic_profile_selected
    )
}
