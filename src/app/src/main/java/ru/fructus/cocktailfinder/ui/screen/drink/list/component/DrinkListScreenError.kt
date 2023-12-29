package ru.fructus.cocktailfinder.ui.screen.drink.list.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import ru.fructus.cocktailfinder.ui.screen.drink.DrinkListContract

@Composable
fun DrinkListScreenError(state: DrinkListContract.State.Error) {
    Text(text = state.error)
}