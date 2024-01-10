package ru.fructus.cocktailfinder.domain

import kotlinx.serialization.Serializable
import ru.fructus.cocktailfinder.data.local.DrinkLocalEntity
@Serializable
data class DrinkDto(
    val id: Int,
    val title: String,
    val type: String,
    val typeGlass: String,
    val image: String,
    var isFavorite: Boolean = false,
    val category: String,
    val strIngredient1: String?,

    val strIngredient10: String?,

    val strIngredient11: String?,

    val strIngredient12: String?,

    val strIngredient13: String?,

    val strIngredient14: String?,

    val strIngredient15: String?,

    val strIngredient2: String?,

    val strIngredient3: String?,

    val strIngredient4: String?,

    val strIngredient5: String?,

    val strIngredient6: String?,

    val strIngredient7: String?,

    val strIngredient8: String?,

    val strIngredient9: String?,
) {
    fun toDrinkLocalEntity(): DrinkLocalEntity {
        return DrinkLocalEntity(
            idDrink = id,
            strAlcoholic = type,
            strCategory = category,
            strDrink = title,
            isFavorite = isFavorite,
            strGlass = typeGlass,
            strDrinkThumb = image,
            strIngredient1 = strIngredient1,
            strIngredient2 = strIngredient2,
            strIngredient3 = strIngredient3,
            strIngredient4 = strIngredient4,
            strIngredient5 = strIngredient5,
            strIngredient6 = strIngredient6,
            strIngredient7 = strIngredient7,
            strIngredient8 = strIngredient8,
            strIngredient9 = strIngredient9,
            strIngredient10 = strIngredient10,
            strIngredient11 = strIngredient11,
            strIngredient12 = strIngredient12,
            strIngredient13 = strIngredient13,
            strIngredient14 = strIngredient14,
            strIngredient15 = strIngredient15
        )
    }

}
