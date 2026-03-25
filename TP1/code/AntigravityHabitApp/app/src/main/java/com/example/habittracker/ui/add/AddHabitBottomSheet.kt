package com.example.habittracker.ui.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.habittracker.databinding.FragmentAddHabitBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.datepicker.MaterialDatePicker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

/**
 * Bottom Sheet Dialog acting as a streamlined form for adding a new Habit.
 */
@AndroidEntryPoint
class AddHabitBottomSheet : BottomSheetDialogFragment() {

    private var _binding: FragmentAddHabitBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AddHabitViewModel by viewModels()

    // Using current time as default
    private var selectedDateMillis: Long = System.currentTimeMillis()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddHabitBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListeners()
        observeEvents()
        updateDateText()
    }

    private fun setupListeners() {
        binding.btnSelectDate.setOnClickListener {
            // Material 3 Date Picker
            val datePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select Last Done Date")
                .setSelection(selectedDateMillis)
                .build()

            datePicker.addOnPositiveButtonClickListener { selection ->
                selectedDateMillis = selection
                updateDateText()
            }
            datePicker.show(childFragmentManager, "DATE_PICKER")
        }

        binding.btnSaveHabit.setOnClickListener {
            val name = binding.etHabitName.text.toString()
            if (name.isNotBlank()) {
                viewModel.saveHabit(name, selectedDateMillis)
            } else {
                binding.tilHabitName.error = "Name cannot be empty"
            }
        }
    }

    private fun updateDateText() {
        val instant = Instant.ofEpochMilli(selectedDateMillis)
        val date = instant.atZone(ZoneId.systemDefault()).toLocalDate()
        
        val formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)
        binding.tvSelectedDate.text = "Last Done: ${date.format(formatter)}"
    }

    private fun observeEvents() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                // Listen for successful save and dismiss dialog
                viewModel.saveSuccessEvent.collect {
                    dismiss()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
