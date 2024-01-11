package ru.fructus.cocktailfinder.ui.screen.drink.list.component

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import ru.fructus.cocktailfinder.R

@Composable
fun DrinkListScreenNoItems(getRandomDrink: () -> Unit) {
    Text(text = stringResource(id = R.string.no_items))
    Button(onClick = { getRandomDrink() }) {
        Text(text = stringResource(id = R.string.retry))
    }
}