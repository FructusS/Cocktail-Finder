package ru.fructus.cocktailfinder.data.repository

import ru.fructus.cocktailfinder.data.remote.DrinkApi
import ru.fructus.cocktailfinder.domain.DrinkDto
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DrinkRepository @Inject constructor(private val drinkApi: DrinkApi) {

    suspend fun getRandomDrinkList() : List<DrinkDto>{
        return drinkApi.getRandomDrinkList().map { it.toDrinkDto() }
    }

}