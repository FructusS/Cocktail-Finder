package ru.fructus.cocktailfinder.data.remote

import retrofit2.http.GET
import ru.fructus.cocktailfinder.domain.DrinkEntity

interface DrinkApi {

    @GET
    suspend fun getRandomDrinkList() : List<DrinkEntity>

}