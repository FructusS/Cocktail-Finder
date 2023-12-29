package ru.fructus.cocktailfinder.data.remote

import retrofit2.http.GET
import ru.fructus.cocktailfinder.domain.DrinkEntity
import ru.fructus.cocktailfinder.domain.DrinkListEntity

interface DrinkApi {

    @GET("random.php")
    suspend fun getRandomDrinkList() : DrinkListEntity?

}