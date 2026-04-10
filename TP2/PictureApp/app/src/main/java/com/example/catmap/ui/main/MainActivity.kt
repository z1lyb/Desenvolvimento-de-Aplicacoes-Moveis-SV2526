package com.example.catmap.ui.main

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.catmap.databinding.ActivityMainBinding

/**
 * Entry point of the app.
 * Displays a grid of cat images fetched from The Cat API.
 *
 * Responsibilities:
 *  - Set up the Toolbar.
 *  - Observe [MainViewModel] LiveData and drive the UI.
 *  - Handle FAB refresh click.
 *  - Handle search menu item click (Step 10).
 *
 * RecyclerView adapter and full observation logic will be added in Steps 4–7.
 */
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up Toolbar as the ActionBar
        setSupportActionBar(binding.toolbar)

        // Observe loading state
        viewModel.isLoading.observe(this) { loading ->
            binding.progressBar.visibility = if (loading) View.VISIBLE else View.GONE
        }

        // FAB refresh – logic wired in Step 8
        binding.fabRefresh.setOnClickListener {
            // TODO: trigger image refresh in Step 8
        }
    }
}
