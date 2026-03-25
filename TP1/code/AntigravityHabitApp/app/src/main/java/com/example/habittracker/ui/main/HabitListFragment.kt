package com.example.habittracker.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.habittracker.R
import com.example.habittracker.databinding.FragmentHabitListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * Main fragment displaying the user's habits.
 */
@AndroidEntryPoint
class HabitListFragment : Fragment() {

    private var _binding: FragmentHabitListBinding? = null
    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    // Inject ViewModel
    private val viewModel: HabitListViewModel by viewModels()
    private lateinit var adapter: HabitAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHabitListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupListeners()
        observeState()
    }

    private fun setupRecyclerView() {
        adapter = HabitAdapter(
            onCompleteClicked = { habit ->
                viewModel.completeHabitToday(habit.id)
            }
        )
        binding.recyclerViewHabits.adapter = adapter
    }

    private fun setupListeners() {
        binding.fabAddHabit.setOnClickListener {
            // Navigate to the bottom sheet using Navigation Component
            findNavController().navigate(R.id.action_habitListFragment_to_addHabitBottomSheet)
        }
    }

    private fun observeState() {
        // Safely collect Flow in the UI layer respecting the View lifecycle
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.habits.collectLatest { habits ->
                    adapter.submitList(habits)
                    // Toggle empty state visibility
                    if (habits.isEmpty()) {
                        binding.tvEmptyState.visibility = View.VISIBLE
                        binding.recyclerViewHabits.visibility = View.GONE
                    } else {
                        binding.tvEmptyState.visibility = View.GONE
                        binding.recyclerViewHabits.visibility = View.VISIBLE
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Prevent memory leaks
        _binding = null
    }
}
