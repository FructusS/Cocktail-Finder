package ru.fructus.cocktailfinder.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.fructus.cocktailfinder.ui.screen.drink.list.DrinkListScreen

@Composable
fun CocktailFinderNavHost(
    navController: NavHostController, startDestination: String = DrinkListScreen.route
) {
    NavHost(
        navController = navController, startDestination = startDestination
    ) {
        composable(DrinkListScreen.route) {
            DrinkListScreen(navigateToDrinkDetailsScreen = {
                navController.navigate(
                    DrinkDetailsScreen.route
                )
            })
        }
    }
}