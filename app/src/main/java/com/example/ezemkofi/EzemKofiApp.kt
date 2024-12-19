package com.example.ezemkofi

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.ezemkofi.ui.navigation.Screen
import com.example.ezemkofi.ui.screen.cart.CartScreen
import com.example.ezemkofi.ui.screen.detail.DetailScreen
import com.example.ezemkofi.ui.screen.home.HomeScreen
import com.example.ezemkofi.ui.screen.profile.ProfileScreen
import com.example.ezemkofi.ui.screen.search.SearchScreen

private const val COFFEE_ID = "coffeeId"

@Composable
fun EzemKofiApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    Scaffold(
        modifier = modifier
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = modifier.padding(paddingValues)
        ){
            //Home
            composable(Screen.Home.route){ HomeScreen(
                navigateToSearch = {
                    navController.navigate(Screen.Search.route)
                },
                navigateToCart = {
                    navController.navigate(Screen.Cart.route)
                },
                navigateToProfile = {
                    navController.navigate(Screen.Profile.route)
                },
                navigateToDetail = { coffeeId ->
                    navController.navigate(Screen.CoffeeDetail.createRoute(coffeeId))
                }
            )}

            //Cart
            composable(Screen.Cart.route){ CartScreen(
                onBack = {
                    navController.navigateUp()
                }
            )}

            //Profile
            composable(Screen.Profile.route){ ProfileScreen(
                onBack = {
                    navController.navigateUp()
                }
            )}

            //Search
            composable(Screen.Search.route){ SearchScreen(
                onBack = {
                    navController.navigateUp()
                },
                navigateToDetail = { coffeeId->
                    navController.navigate(Screen.CoffeeDetail.createRoute(coffeeId))
                }
            )}

            //Detail
            composable(
                Screen.CoffeeDetail.route,
                arguments = listOf(
                    navArgument(COFFEE_ID){
                        type = NavType.IntType
                    }
                )
            ){
                val id = it.arguments?.getInt(COFFEE_ID) ?: -1
                DetailScreen(
                    coffeeId = id,
                    onBack = {
                        navController.navigateUp()
                    },
                    navigateToCart = {
                        navController.navigate(Screen.Cart.route){
                            popUpTo(Screen.CoffeeDetail.createRoute(coffeeId = id)){
                                inclusive = true
                            }
                        }
                    }
                )
            }
        }
    }
}