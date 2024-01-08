package ru.fructus.cocktailfinder.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface DrinkDao {
    @Query("select * from drinks")
    suspend fun getDrinkList() : List<DrinkLocalEntity>?
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addDrink(drinkEntity: DrinkLocalEntity)
    @Update
    suspend fun updateDrink(drinkLocalEntity: DrinkLocalEntity) : Int
}
