package ru.fructus.cocktailfinder.data.remote

import retrofit2.http.GET
import ru.fructus.cocktailfinder.domain.DrinkRemoteEntityList

interface DrinkApi {

    @GET("random.php")
    suspend fun getRandomDrink() : DrinkRemoteEntityList?

}