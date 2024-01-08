package ru.fructus.cocktailfinder.ui.screen.drink.list

import ru.fructus.cocktailfinder.domain.DrinkDto
import ru.fructus.cocktailfinder.ui.base.BaseContract

sealed interface DrinkListContract :
    BaseContract<DrinkListContract.State, DrinkListContract.Event, DrinkListContract.Effect> {
    sealed class Effect {
        data object PullToRefresh : Effect()
    }

    sealed class Event {
        data class OnDrinkClick(val id: Int) : Event()
        data object OnRefresh : Event()
        data object OnEnterScreen : Event()
    }

    sealed class State {
        object Loading : State()
        object NoItems : State()

        data class Success(val drinks: List<DrinkDto>) : State()
        data class Error(val error: String) : State()
    }
}

