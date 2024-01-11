package ru.fructus.cocktailfinder.ui.screen.drink.list.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import ru.fructus.cocktailfinder.R
import ru.fructus.cocktailfinder.ui.screen.drink.list.DrinkListContract

@Composable
fun DrinkListScreenError(state: DrinkListContract.State.Error, getRandomDrink: () -> Unit) {
    Box(contentAlignment = Alignment.Center){
        Column(horizontalAlignment = Alignment.CenterHorizontally){
            Text(text = state.error, textAlign = TextAlign.Center)
            Button(onClick = { getRandomDrink() }) {
                Text(text = stringResource(id = R.string.retry))
            }
        }
    }

}