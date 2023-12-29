package ru.fructus.cocktailfinder.ui.screen.drink.list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import ru.fructus.cocktailfinder.ui.screen.drink.list.component.DrinkListScreenError
import ru.fructus.cocktailfinder.ui.screen.drink.list.component.DrinkListScreenLoading
import ru.fructus.cocktailfinder.ui.screen.drink.list.component.DrinkListScreenNoItems
import ru.fructus.cocktailfinder.ui.screen.drink.list.component.DrinkListScreenSuccess

@Composable
fun DrinkListScreen(
    viewModel: DrinkListViewModel = hiltViewModel(), navigateToDrinkDetailsScreen: () -> Unit
) {
    val state = viewModel.state.collectAsState()

    LaunchedEffect(key1 = state) {
        viewModel.event(DrinkListContract.Event.OnEnterScreen)
    }
    
    when(val currentState = state.value){
        is DrinkListContract.State.Success -> {
            DrinkListScreenSuccess(state = currentState)
        }
        is DrinkListContract.State.Error -> {
            DrinkListScreenError(state = currentState)
        }
        DrinkListContract.State.Loading -> {
            DrinkListScreenLoading(state = currentState)
        }
        DrinkListContract.State.NoItems -> {
            DrinkListScreenNoItems(state = currentState)
        }
    }

    
    
}