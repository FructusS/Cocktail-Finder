package ru.fructus.cocktailfinder.ui.screen.drink.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.fructus.cocktailfinder.data.repository.DrinkRepository
import javax.inject.Inject

@HiltViewModel
class DrinkListViewModel @Inject constructor(private val drinkRepository: DrinkRepository) :
    ViewModel(), DrinkListContract {
    private val _state: MutableStateFlow<DrinkListContract.State> =
        MutableStateFlow(DrinkListContract.State.Loading)
    override val state: StateFlow<DrinkListContract.State> = _state.asStateFlow()

    private val _effect = MutableSharedFlow<DrinkListContract.Effect>()
    override val effect: SharedFlow<DrinkListContract.Effect> = _effect.asSharedFlow()
    override fun event(event: DrinkListContract.Event) {
        return when (event) {
            is DrinkListContract.Event.OnRandomDrinkButtonClick -> {

                onRandomDrinkButtonClick()

            }

            DrinkListContract.Event.OnEnterScreen -> {
                getDrinkList()
            }

            DrinkListContract.Event.OnRefresh -> {
                getDrinkList()
            }
        }
    }

    private fun onRandomDrinkButtonClick() {
        viewModelScope.launch {
            _effect.emit(DrinkListContract.Effect.OnRandomDrinkButtonClick)
        }
    }

    private fun getDrinkList() {
        viewModelScope.launch {
            _state.update {
                DrinkListContract.State.Loading
            }
            try {
                val response = drinkRepository.getDrinkList()

                if (response.isNullOrEmpty()) {
                    _state.update {
                        DrinkListContract.State.NoItems
                    }
                } else {
                    _state.update {
                        DrinkListContract.State.Success(response)
                    }
                }
            } catch (ex: Exception) {
                _state.update {
                    DrinkListContract.State.Error(ex.localizedMessage)
                }
            }
        }
    }
}



