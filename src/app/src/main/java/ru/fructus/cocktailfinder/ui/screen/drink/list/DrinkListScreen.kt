package ru.fructus.cocktailfinder.ui.screen.drink.list

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.flow.collectLatest
import ru.fructus.cocktailfinder.R
import ru.fructus.cocktailfinder.ui.screen.drink.list.component.DrinkListScreenError
import ru.fructus.cocktailfinder.ui.screen.drink.list.component.DrinkListScreenLoading
import ru.fructus.cocktailfinder.ui.screen.drink.list.component.DrinkListScreenNoItems
import ru.fructus.cocktailfinder.ui.screen.drink.list.component.DrinkListScreenSuccess

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun DrinkListScreen(
    viewModel: DrinkListViewModel = hiltViewModel(), navigateToDrinkDetailsScreen: () -> Unit
) {
    val state = viewModel.state.collectAsState()
    val effect =
        viewModel.effect.collectAsState(initial = DrinkListContract.Effect.OnRandomDrinkButtonClick)
    val pullToRefreshState = rememberPullRefreshState(
        refreshing = state.value is DrinkListContract.State.Loading,
        onRefresh = {
            viewModel.event(DrinkListContract.Event.OnRefresh)
        })
    LaunchedEffect(key1 = state) {
        viewModel.event(DrinkListContract.Event.OnEnterScreen)
    }
    LaunchedEffect(key1 = effect) {
        viewModel.effect.collectLatest {
            when (it) {

                DrinkListContract.Effect.OnRandomDrinkButtonClick -> {
                    navigateToDrinkDetailsScreen()
                }
            }
        }
    }



    Scaffold(modifier = Modifier.pullRefresh(pullToRefreshState), floatingActionButton = {
        FloatingActionButton(onClick = {
            viewModel.event(DrinkListContract.Event.OnRandomDrinkButtonClick)
        }) {
            Image(
                painter = painterResource(id = R.drawable.ic_random),
                contentDescription = stringResource(
                    id = R.string.random_drink
                )
            )
        }
    }) {

        PullRefreshIndicator(
            state.value is DrinkListContract.State.Loading,
            pullToRefreshState,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth()
                .wrapContentHeight()
                .zIndex(100f)
        )

        when (val currentState = state.value) {
            is DrinkListContract.State.Success -> {
                DrinkListScreenSuccess(state = currentState, viewModel)
            }

            is DrinkListContract.State.Error -> {
                DrinkListScreenError(state = currentState,
                    getRandomDrink = { viewModel.event(DrinkListContract.Event.OnRefresh) })
            }

            DrinkListContract.State.Loading -> {
                DrinkListScreenLoading()
            }

            DrinkListContract.State.NoItems -> {
                DrinkListScreenNoItems(getRandomDrink = { viewModel.event(DrinkListContract.Event.OnRefresh) })
            }
        }
    }
}