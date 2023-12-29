package ru.fructus.cocktailfinder.ui.screen.drink.list.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import ru.fructus.cocktailfinder.ui.screen.drink.DrinkListContract

@Composable
fun DrinkListScreenNoItems(state: DrinkListContract.State) {
    Text(text = "no items")
}