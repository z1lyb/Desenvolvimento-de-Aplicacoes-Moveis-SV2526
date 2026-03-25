package com.example.habittracker.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.habittracker.domain.model.Habit

/**
 * The Room database definition.
 * Ties the entities and their respective DAOs together.
 */
@Database(entities = [Habit::class], version = 1, exportSchema = false)
abstract class HabitDatabase : RoomDatabase() {

    // Exposes the HabitDao for use in the repository
    abstract fun habitDao(): HabitDao
}
