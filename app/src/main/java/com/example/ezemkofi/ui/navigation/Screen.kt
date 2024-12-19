package com.example.ezemkofi.ui.navigation

sealed class Screen(val route: String) {
    object Home: Screen("home")
    object Cart: Screen("cart")
    object Profile: Screen("profile")
    object Search: Screen("search")
    object CoffeeDetail: Screen("home/{coffeeId}"){
        fun createRoute(coffeeId: Int) = "home/$coffeeId"
    }
}
