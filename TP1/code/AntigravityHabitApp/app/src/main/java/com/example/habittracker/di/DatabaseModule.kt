package com.example.habittracker.di

import android.content.Context
import androidx.room.Room
import com.example.habittracker.data.database.HabitDao
import com.example.habittracker.data.database.HabitDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Hilt module to provide singleton instances of database components.
 */
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    // Builds the Room database instance tied to the application lifecycle
    @Provides
    @Singleton
    fun provideHabitDatabase(
        @ApplicationContext context: Context
    ): HabitDatabase {
        return Room.databaseBuilder(
            context,
            HabitDatabase::class.java,
            "habit_database"
        ).build()
    }

    // Provides the DAO from the complete database instance to be easily injected in ViewModels/Repositories
    @Provides
    @Singleton
    fun provideHabitDao(database: HabitDatabase): HabitDao {
        return database.habitDao()
    }
}
