package com.example.habittracker.data.repository

import com.example.habittracker.domain.model.Habit
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface mapping the contract for data operations on Habits.
 * Following Clean Architecture, domain layers interact with interfaces, not concrete implementations.
 */
interface HabitRepository {
    // Flow emitting list of all habits. Automatically reacting to database changes.
    fun getAllHabits(): Flow<List<Habit>>

    // Suspend function to insert a single habit safely off the main thread.
    suspend fun addHabit(habit: Habit)

    // Complete the habit by setting its done time to the provided timestamp.
    suspend fun completeHabit(habitId: Int, timestampMillis: Long)
}
