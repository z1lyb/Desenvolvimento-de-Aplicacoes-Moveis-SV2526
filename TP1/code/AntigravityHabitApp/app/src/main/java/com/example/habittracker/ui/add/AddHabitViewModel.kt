package com.example.habittracker.ui.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.habittracker.data.repository.HabitRepository
import com.example.habittracker.domain.model.Habit
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for adding a new habit.
 */
@HiltViewModel
class AddHabitViewModel @Inject constructor(
    private val repository: HabitRepository
) : ViewModel() {

    // Event flow for completing the save action
    private val _saveSuccessEvent = MutableSharedFlow<Unit>()
    val saveSuccessEvent: SharedFlow<Unit> = _saveSuccessEvent

    /**
     * Creates and stores a new habit securely.
     */
    fun saveHabit(name: String, lastDoneTimeMillis: Long) {
        if (name.isBlank()) return // Simple validation

        viewModelScope.launch {
            val habit = Habit(
                name = name.trim(),
                lastDoneTimeMillis = lastDoneTimeMillis
            )
            repository.addHabit(habit)
            // Emit success event to close screen
            _saveSuccessEvent.emit(Unit)
        }
    }
}
