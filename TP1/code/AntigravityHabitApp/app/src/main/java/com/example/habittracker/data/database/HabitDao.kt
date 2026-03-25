package com.example.habittracker.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.habittracker.domain.model.Habit
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object for the Habit entity.
 * Provides the SQLite query implementations exposed via Coroutines & Flow.
 */
@Dao
interface HabitDao {

    // Retrieves all habits ordered by name ascending for cleaner UX.
    // Returning Flow means Room handles querying async and emits newly updated lists automatically upon database changes.
    @Query("SELECT * FROM habits ORDER BY name ASC")
    fun getAllHabits(): Flow<List<Habit>>

    // Inserts a new habit or replaces it if a conflict occurs on ID.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHabit(habit: Habit)

    // Helper method to rapidly update the completion time for a given habit ID.
    @Query("UPDATE habits SET lastDoneTimeMillis = :timestamp WHERE id = :habitId")
    suspend fun updateLastDoneTime(habitId: Int, timestamp: Long)
}
