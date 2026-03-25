package com.example.habittracker.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Represents a single Habit tracked by the user.
 * Annotated with @Entity to map this class to a SQLite table managed by Room.
 */
@Entity(tableName = "habits")
data class Habit(
    // The unique ID for the habit. Auto-incremented by Room.
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    
    // The user-provided name of the habit (e.g., "Drink Water")
    val name: String,
    
    // The timestamp covering when this habit was last completed.
    // Stored as milliseconds since Unix epoch for simpler mathematical comparisons and storage.
    val lastDoneTimeMillis: Long
)
