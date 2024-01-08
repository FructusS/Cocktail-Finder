package ru.fructus.cocktailfinder.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [DrinkLocalEntity::class], version = 2)
abstract class DrinkDatabase : RoomDatabase(){
    abstract fun drinkDao() : DrinkDao
}