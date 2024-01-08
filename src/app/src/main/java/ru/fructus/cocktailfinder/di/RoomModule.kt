package ru.fructus.cocktailfinder.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.fructus.cocktailfinder.data.local.DrinkDao
import ru.fructus.cocktailfinder.data.local.DrinkDatabase

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Provides
    fun provideRoomDatabase(@ApplicationContext context: Context): DrinkDatabase {
        return Room.databaseBuilder(context, DrinkDatabase::class.java, DATABASE_NAME).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideDrinkDao(drinkDatabase: DrinkDatabase): DrinkDao {
        return drinkDatabase.drinkDao()
    }
}

const val DATABASE_NAME = "drink_database"
