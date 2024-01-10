package ru.fructus.cocktailfinder.ui.navigation

interface NavigationDestination{
    val route: String
    val titleScreen: Int?
}

object DrinkListDestination : NavigationDestination {
    override val route: String = "drink_list_screen"
    override val titleScreen: Int? = null
}
object DrinkDetailsDestination : NavigationDestination {
    override val route: String = "drink_details_screen"
    override val titleScreen: Int? = null
    const val drinkId = "drinkId"
    val routeWithArgs = "$route/{$drinkId}"
}