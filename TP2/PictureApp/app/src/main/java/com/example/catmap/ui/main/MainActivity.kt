package com.example.catmap.ui.main

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.catmap.databinding.ActivityMainBinding

/**
 * Entry point of the app.
 * Displays a grid of cat images fetched from The Cat API.
 *
 * Responsibilities:
 *  - Set up the Toolbar.
 *  - Observe [MainViewModel] LiveData and drive the UI.
 *  - Handle FAB refresh click.
 *  - Connect [ImageAdapter] to RecyclerView to display the grid of images (Step 7).
 *  - Handle search menu item click (Step 10).
 */
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var imageAdapter: ImageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up Toolbar as the ActionBar
        setSupportActionBar(binding.toolbar)

        setupRecyclerView()
        setupObservers()

        // Setup Retry button
        binding.buttonRetry.setOnClickListener {
            viewModel.fetchImages()
        }

        // FAB refresh – logic wired in Step 8
        binding.fabRefresh.setOnClickListener {
            // TODO: trigger image refresh in Step 8
        }
    }

    private fun setupRecyclerView() {
        // Initialize adapter with an empty click listener for now (Step 8 will add navigation)
        imageAdapter = ImageAdapter { clickedImage ->
            // TODO: Navigate to detail screen in Step 8
        }

        // Setup RecyclerView with GridLayoutManager (e.g., 2 columns visually appealing)
        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 2)
            adapter = imageAdapter
        }
    }

    private fun setupObservers() {
        // Observe images
        viewModel.images.observe(this) { imagesList ->
            if (imagesList != null && imagesList.isNotEmpty()) {
                imageAdapter.setImages(imagesList)
                // We only explicitly show the recycler view if not loading and no error
                // The loading state observer mainly handles the visibility toggles
            }
        }

        // Observe loading state to toggle visible views
        viewModel.isLoading.observe(this) { isLoading ->
            if (isLoading) {
                binding.progressBar.visibility = View.VISIBLE
                binding.recyclerView.visibility = View.GONE
                binding.layoutError.visibility = View.GONE
            } else {
                binding.progressBar.visibility = View.GONE
                // If there's an error, the error observer will handle visibility.
                // Otherwise, show the recycler view. We could check images list size here to be safer.
                if (viewModel.errorMessage.value == null) {
                    binding.recyclerView.visibility = View.VISIBLE
                }
            }
        }

        // Observe error messages
        viewModel.errorMessage.observe(this) { errorMsg ->
            if (errorMsg != null) {
                binding.layoutError.visibility = View.VISIBLE
                binding.textErrorMessage.text = errorMsg
                binding.recyclerView.visibility = View.GONE
                // ProgressBar is handled by isLoading
            } else {
                binding.layoutError.visibility = View.GONE
            }
        }
    }
}
