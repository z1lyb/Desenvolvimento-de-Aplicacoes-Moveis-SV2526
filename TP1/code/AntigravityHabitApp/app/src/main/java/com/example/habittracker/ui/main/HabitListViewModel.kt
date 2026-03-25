package com.example.habittracker.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.habittracker.data.repository.HabitRepository
import com.example.habittracker.domain.model.Habit
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel responsible for managing UI state for the Habit List screen.
 */
@HiltViewModel
class HabitListViewModel @Inject constructor(
    private val repository: HabitRepository
) : ViewModel() {

    // Expose the list of habits as a StateFlow for the UI to observe.
    // 'stateIn' converts the cold Flow from Room into a hot StateFlow scoped to the ViewModel.
    val habits: StateFlow<List<Habit>> = repository.getAllHabits()
        .stateIn(
            scope = viewModelScope,
            // Keep the flow active for 5 seconds after all collectors disappear to avoid quick restarts on config changes
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    /**
     * Marks a habit as completed as of the current time.
     */
    fun completeHabitToday(habitId: Int) {
        viewModelScope.launch {
            repository.completeHabit(habitId, System.currentTimeMillis())
        }
    }
}
