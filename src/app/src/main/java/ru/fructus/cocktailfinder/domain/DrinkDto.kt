package ru.fructus.cocktailfinder.domain

data class DrinkDto(
    val id: Int,
    val title: String,
    val type: String,
    val typeGlass: String,
    val image: String,
    var isFavorite: Boolean = false,
    val category: String
)
