package com.example.habittracker.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.habittracker.databinding.ItemHabitBinding
import com.example.habittracker.domain.model.Habit
import java.time.Instant
import java.time.ZoneId
import java.time.temporal.ChronoUnit

/**
 * Adapter for displaying habits in a RecyclerView.
 * Uses ListAdapter and DiffUtil for optimal performance during list updates.
 */
class HabitAdapter(
    // Callback when the user clicks 'Done Today'
    private val onCompleteClicked: (Habit) -> Unit
) : ListAdapter<Habit, HabitAdapter.HabitViewHolder>(HabitDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitViewHolder {
        val binding = ItemHabitBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return HabitViewHolder(binding, onCompleteClicked)
    }

    override fun onBindViewHolder(holder: HabitViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class HabitViewHolder(
        private val binding: ItemHabitBinding,
        private val onCompleteClicked: (Habit) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(habit: Habit) {
            binding.tvHabitName.text = habit.name

            // Calculate days since the habit was last completed.
            // Using java.time to handle calendar math correctly.
            val lastDoneInstant = Instant.ofEpochMilli(habit.lastDoneTimeMillis)
            val nowInstant = Instant.now()

            // Compare local dates to avoid time-of-day shifting bugs
            val zoneId = ZoneId.systemDefault()
            val lastDoneDate = lastDoneInstant.atZone(zoneId).toLocalDate()
            val nowDate = nowInstant.atZone(zoneId).toLocalDate()

            val daysSince = ChronoUnit.DAYS.between(lastDoneDate, nowDate).toInt()

            binding.tvDaysSince.text = when {
                daysSince <= 0 -> "Completed Today"
                daysSince == 1 -> "1 day ago"
                else -> "$daysSince days ago"
            }

            // Disable button if already done today
            binding.btnCompleteNow.isEnabled = daysSince > 0
            
            binding.btnCompleteNow.setOnClickListener {
                onCompleteClicked(habit)
            }
        }
    }
}

// DiffUtil callback for calculating the difference between two non-null items in a list.
class HabitDiffCallback : DiffUtil.ItemCallback<Habit>() {
    override fun areItemsTheSame(oldItem: Habit, newItem: Habit): Boolean {
        // ID is the unique identifier
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Habit, newItem: Habit): Boolean {
        // Check if data changed
        return oldItem == newItem
    }
}
