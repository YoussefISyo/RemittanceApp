package com.isyo.remitconnecct.uis.mainNavigation

sealed class Screens(val screen: String) {
    data object Home: Screens("home")
    data object Cards: Screens("cards")
    data object Tontines: Screens("tontines")
    data object Settings: Screens("settings")
}