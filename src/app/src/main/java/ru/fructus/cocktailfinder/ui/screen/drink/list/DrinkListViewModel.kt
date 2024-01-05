package ru.fructus.cocktailfinder.ui.screen.drink.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.fructus.cocktailfinder.data.repository.DrinkRepository
import ru.fructus.cocktailfinder.ui.screen.drink.DrinkListContract
import javax.inject.Inject

@HiltViewModel
class DrinkListViewModel @Inject constructor(private val drinkRepository: DrinkRepository) :
    ViewModel(),
    DrinkListContract {

    private val _state = MutableStateFlow<DrinkListContract.State>(DrinkListContract.State.Loading)
    override val state: StateFlow<DrinkListContract.State> = _state.asStateFlow()

        private val _effect = MutableSharedFlow<DrinkListContract.Effect>()
    override val effect: SharedFlow<DrinkListContract.Effect> = _effect.asSharedFlow()


    override fun event(event: DrinkListContract.Event) = when (event) {
        DrinkListContract.Event.OnEnterScreen -> {
            getRandomDrink()
        }

        is DrinkListContract.Event.OnFavoriteClick -> {
            _state.update {
                DrinkListContract.State.Success(listOf(event.drink.copy(isFavorite = !event.drink.isFavorite)))
            }
        }

        is DrinkListContract.Event.OnRefresh -> {
            getRandomDrink()
        }

    }

    fun getRandomDrink() {
        viewModelScope.launch(Dispatchers.IO) {
            _state.update {
                DrinkListContract.State.Loading
            }
            try {
                val response = drinkRepository.getRandomDrinkList()
                if (response.isNullOrEmpty()) {
                    _state.update {
                        DrinkListContract.State.NoItems

                    }
                } else {
                    _state.update {
                        DrinkListContract.State.Success(response)
                    }
                }

            } catch (e: Exception) {
                _state.update {
                    DrinkListContract.State.Error(e.localizedMessage)
                }
            }
        }
    }
}



