package ru.fructus.cocktailfinder.domain

import com.google.gson.annotations.SerializedName

data class DrinkRemoteEntityList(
    @SerializedName("drinks")
    val drinks : List<DrinkRemoteEntity>
)