package ru.fructus.cocktailfinder.ui.screen.drink.details

import ru.fructus.cocktailfinder.domain.DrinkDto
import ru.fructus.cocktailfinder.ui.base.BaseContract

interface DrinkDetailsContract :
    BaseContract<DrinkDetailsContract.State, DrinkDetailsContract.Event, DrinkDetailsContract.Effect> {
    sealed class Effect {

    }

    sealed class Event {
        data class OnFavoriteClick(val drink: DrinkDto) : Event()
        data object OnRefresh : Event()
        data object OnEnterScreen : Event()
    }

    sealed class State {
        object Loading : State()
        object NoItems : State()

        data class Success(val drink: DrinkDto) : State()
        data class Error(val error: String) : State()
    }
}

