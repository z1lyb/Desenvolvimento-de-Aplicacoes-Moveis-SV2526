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
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setLogo(com.example.catmap.R.drawable.cat_icon)
        supportActionBar?.setDisplayUseLogoEnabled(true)

        setupRecyclerView()
        setupObservers()

        // Setup Retry button
        binding.buttonRetry.setOnClickListener {
            viewModel.fetchImages()
        }

        // FAB refresh – logic wired in Step 9
        binding.fabRefresh.setOnClickListener {
            viewModel.fetchImages()
        }
    }

    override fun onResume() {
        super.onResume()
        // Refresh favorites in case user toggled them on the Favorites screen
        viewModel.refreshFavorites()
    }

    private fun setupRecyclerView() {
        // Initialize adapter with listeners for details and favorites
        imageAdapter = ImageAdapter(
            onItemClick = { clickedImage ->
                val intent = android.content.Intent(this, com.example.catmap.ui.detail.DetailActivity::class.java).apply {
                    putExtra("EXTRA_IMAGE_JSON", com.google.gson.Gson().toJson(clickedImage))
                }
                startActivity(intent)
            },
            onFavoriteClick = { item ->
                viewModel.toggleFavorite(item)
            }
        )

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
            }
        }

        // Observe favorite IDs to update heart icons in the grid
        viewModel.favoriteIds.observe(this) { ids ->
            imageAdapter.setFavorites(ids)
        }

        // Observe loading state to toggle visible views
        viewModel.isLoading.observe(this) { isLoading ->
            if (isLoading) {
                binding.progressBar.visibility = View.VISIBLE
                binding.recyclerView.visibility = View.GONE
                binding.layoutError.visibility = View.GONE
            } else {
                binding.progressBar.visibility = View.GONE
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
            } else {
                binding.layoutError.visibility = View.GONE
            }
        }

        // Observe loading progress for the linear indicator
        viewModel.loadingProgress.observe(this) { progress ->
            binding.progressBar.setProgress(progress, true)
        }
    }

    override fun onOptionsItemSelected(item: android.view.MenuItem): Boolean {
        return when (item.itemId) {
            com.example.catmap.R.id.action_favorites -> {
                startActivity(android.content.Intent(this, com.example.catmap.ui.favorites.FavoritesActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: android.view.Menu): Boolean {
        menuInflater.inflate(com.example.catmap.R.menu.menu_main, menu)

        val searchItem = menu.findItem(com.example.catmap.R.id.action_search)
        val searchView = searchItem.actionView as androidx.appcompat.widget.SearchView
        searchView.queryHint = getString(com.example.catmap.R.string.search_hint)

        searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                imageAdapter.filter(newText ?: "")
                return true
            }
        })

        return super.onCreateOptionsMenu(menu)
    }
}
