package ru.fructus.cocktailfinder.domain

import com.google.gson.annotations.SerializedName

data class DrinkListEntity(
    @SerializedName("drinks")
    val drinks : List<DrinkEntity>
)