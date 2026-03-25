package com.example.habittracker.data.repository

import com.example.habittracker.data.database.HabitDao
import com.example.habittracker.domain.model.Habit
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Concrete implementation of HabitRepository pulling from our Room DAO.
 * @Inject injects dependencies via Dagger-Hilt.
 */
class HabitRepositoryImpl @Inject constructor(
    private val habitDao: HabitDao
) : HabitRepository {

    // Pass through the reactive Flow directly from Room
    override fun getAllHabits(): Flow<List<Habit>> = habitDao.getAllHabits()

    // Pass the insert down
    override suspend fun addHabit(habit: Habit) {
        habitDao.insertHabit(habit)
    }

    // Pass the completion timestamp down
    override suspend fun completeHabit(habitId: Int, timestampMillis: Long) {
        habitDao.updateLastDoneTime(habitId, timestampMillis)
    }
}
