package ru.fructus.cocktailfinder.ui.screen.drink.list.component

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import ru.fructus.cocktailfinder.ui.screen.drink.DrinkListContract

@Composable
fun DrinkListScreenLoading(state: DrinkListContract.State) {
    CircularProgressIndicator()
}