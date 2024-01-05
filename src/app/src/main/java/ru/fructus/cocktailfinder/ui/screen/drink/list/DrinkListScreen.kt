package ru.fructus.cocktailfinder.ui.screen.drink.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.flow.collectLatest
import ru.fructus.cocktailfinder.ui.screen.drink.DrinkListContract
import ru.fructus.cocktailfinder.ui.screen.drink.list.component.DrinkListScreenError
import ru.fructus.cocktailfinder.ui.screen.drink.list.component.DrinkListScreenLoading
import ru.fructus.cocktailfinder.ui.screen.drink.list.component.DrinkListScreenNoItems
import ru.fructus.cocktailfinder.ui.screen.drink.list.component.DrinkListScreenSuccess

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DrinkListScreen(
    viewModel: DrinkListViewModel = hiltViewModel(), navigateToDrinkDetailsScreen: () -> Unit
) {
    val state = viewModel.state.collectAsState()
    val effect by viewModel.effect.collectAsState(
        DrinkListContract.Effect.PullToRefresh
    )
    var isPullToRefresh by remember {
        mutableStateOf(false)
    }
    val pullToRefreshState = rememberPullRefreshState(refreshing = isPullToRefresh, onRefresh = {
        viewModel.event(DrinkListContract.Event.OnRefresh)
    })
    LaunchedEffect(key1 = state) {
        viewModel.event(DrinkListContract.Event.OnEnterScreen)
    }
    LaunchedEffect(key1 = effect) {
        viewModel.effect.collectLatest {
            when (it) {
                is DrinkListContract.Effect.PullToRefresh -> {
                    isPullToRefresh = !isPullToRefresh
                }
            }
        }
    }


    Surface(
        modifier = Modifier
            .pullRefresh(pullToRefreshState)
            .verticalScroll(rememberScrollState())
    ) {
        when (effect) {
            DrinkListContract.Effect.PullToRefresh -> {
                Box(modifier = Modifier.zIndex(1000f)
                ) {
                    PullRefreshIndicator(
                        isPullToRefresh,
                        pullToRefreshState,
                        modifier = Modifier
                            .wrapContentWidth()
                            .wrapContentHeight()
                            .align(Alignment.TopCenter)
                    )
                }
            }
        }

        when (val currentState = state.value) {
            is DrinkListContract.State.Success -> {
                DrinkListScreenSuccess(state = currentState, viewModel)
            }

            is DrinkListContract.State.Error -> {
                DrinkListScreenError(state = currentState,
                    getRandomDrink = { viewModel.getRandomDrink() })
            }

            DrinkListContract.State.Loading -> {
                DrinkListScreenLoading()
            }

            DrinkListContract.State.NoItems -> {
                DrinkListScreenNoItems(getRandomDrink = { viewModel.getRandomDrink() })
            }
        }
    }
}