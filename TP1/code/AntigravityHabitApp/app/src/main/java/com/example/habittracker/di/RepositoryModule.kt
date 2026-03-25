package com.example.habittracker.di

import com.example.habittracker.data.repository.HabitRepository
import com.example.habittracker.data.repository.HabitRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Hilt module binding interface implementations.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    // Binds the repository implementation to its interface so when a dependency needs HabitRepository,
    // Hilt knows to supply HabitRepositoryImpl.
    @Binds
    @Singleton
    abstract fun bindHabitRepository(
        habitRepositoryImpl: HabitRepositoryImpl
    ): HabitRepository
}
