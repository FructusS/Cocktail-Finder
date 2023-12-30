package ru.fructus.cocktailfinder.ui.screen.drink.list.component

import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.fructus.cocktailfinder.ui.screen.drink.DrinkListContract

@Composable
fun DrinkListScreenLoading(state: DrinkListContract.State) {
    CircularProgressIndicator(modifier = Modifier.wrapContentWidth().wrapContentHeight())
}