package ru.fructus.cocktailfinder.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import ru.fructus.cocktailfinder.ui.screen.drink.details.DrinkDetailsScreen
import ru.fructus.cocktailfinder.ui.screen.drink.list.DrinkListScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CocktailFinderNavHost(
    navController: NavHostController, startDestination: String = DrinkListDestination.route
) {
    NavHost(
        navController = navController, startDestination = startDestination
    ) {
        composable(DrinkDetailsDestination.route) {
            DrinkDetailsScreen(navigateBack = { navController.popBackStack() })
        }

        composable(DrinkListDestination.route) {
            DrinkListScreen(navigateToDrinkDetailsScreen = {
                navController.navigate(
                    DrinkDetailsDestination.route
                )
            })
        }
    }
}