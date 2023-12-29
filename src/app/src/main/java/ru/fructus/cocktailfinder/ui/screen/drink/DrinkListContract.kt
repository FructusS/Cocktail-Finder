package ru.fructus.cocktailfinder.ui.screen.drink

import ru.fructus.cocktailfinder.domain.DrinkDto
import ru.fructus.cocktailfinder.ui.base.BaseContract

interface DrinkListContract :
    BaseContract<DrinkListContract.State, DrinkListContract.Event, DrinkListContract.Effect> {
    sealed class Effect {
        object OnBackPressed : Effect()
        data class ShowToast(val message: String) : Effect()
    }

    sealed class Event {
        data class OnFavoriteClick(val drink: DrinkDto) : Event()
        object OnRefresh : Event()
        object OnEnterScreen : Event()
        object OnBackPressed : Event()
        data class ShowToast(val message: String) : Event()
    }

    sealed class State {
        object Loading : State()
        object NoItems : State()

        data class Success(val list: List<DrinkDto>) : State()
        data class Error(val error: String) : State()
    }
}

