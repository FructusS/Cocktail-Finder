package ru.fructus.cocktailfinder.ui.screen.drink

import ru.fructus.cocktailfinder.domain.DrinkDto
import ru.fructus.cocktailfinder.ui.base.BaseContract

interface DrinkListContract :
    BaseContract<DrinkListContract.State, DrinkListContract.Event, DrinkListContract.Effect> {
    sealed class Effect {
    }

    sealed class Event {
        data class OnFavoriteClick(val drink: DrinkDto) : Event()
        object OnRefresh : Event()
        object OnEnterScreen : Event()
    }

    sealed class State {
        object Loading : State()
        object NoItems : State()

        data class Success(val list: List<DrinkDto>) : State()
        data class Error(val error: String) : State()
    }
}

