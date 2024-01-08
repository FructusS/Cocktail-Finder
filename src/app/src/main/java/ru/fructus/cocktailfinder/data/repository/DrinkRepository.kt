package ru.fructus.cocktailfinder.data.repository

import ru.fructus.cocktailfinder.data.local.DrinkDao
import ru.fructus.cocktailfinder.data.local.DrinkLocalEntity
import ru.fructus.cocktailfinder.data.remote.DrinkApi
import ru.fructus.cocktailfinder.domain.DrinkDto
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DrinkRepository @Inject constructor(private val drinkApi: DrinkApi, private val drinkDao: DrinkDao) {

    suspend fun getRandomDrink() : DrinkDto? {
        return drinkApi.getRandomDrink()?.drinks?.get(0)?.toDrinkDto()

    }


    suspend fun getDrinkList() : List<DrinkDto>? {
        return drinkDao.getDrinkList()?.map { it.toDrinkDto() }
    }
    suspend fun updateDrink(drinkLocalEntity: DrinkLocalEntity) = drinkDao.updateDrink(drinkLocalEntity)
}