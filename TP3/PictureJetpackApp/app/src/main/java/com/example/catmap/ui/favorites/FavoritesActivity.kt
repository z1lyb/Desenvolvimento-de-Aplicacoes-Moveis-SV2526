package com.example.catmap.ui.favorites

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.catmap.databinding.ActivityFavoritesBinding
import com.example.catmap.ui.main.ImageAdapter

/**
 * Screen displaying the user's favorite cat images (limited to 5, FIFO).
 */
class FavoritesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoritesBinding
    private val viewModel: FavoritesViewModel by viewModels()
    private lateinit var imageAdapter: ImageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoritesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup Toolbar with back button
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        setupRecyclerView()
        setupObservers()
    }

    private fun setupRecyclerView() {
        imageAdapter = ImageAdapter(
            onItemClick = { clickedImage ->
                // Reuse existing detail navigation logic
                val intent = android.content.Intent(this, com.example.catmap.ui.detail.DetailActivity::class.java).apply {
                    putExtra("EXTRA_IMAGE_JSON", com.google.gson.Gson().toJson(clickedImage))
                }
                startActivity(intent)
            },
            onFavoriteClick = { item ->
                viewModel.toggleFavorite(item)
            }
        )

        binding.recyclerViewFavorites.apply {
            layoutManager = GridLayoutManager(this@FavoritesActivity, 2)
            adapter = imageAdapter
        }
    }

    private fun setupObservers() {
        viewModel.favorites.observe(this) { favorites ->
            if (favorites.isNullOrEmpty()) {
                binding.recyclerViewFavorites.visibility = View.GONE
                binding.textNoFavorites.visibility = View.VISIBLE
            } else {
                binding.recyclerViewFavorites.visibility = View.VISIBLE
                binding.textNoFavorites.visibility = View.GONE
                imageAdapter.setImages(favorites)
            }
        }

        viewModel.favoriteIds.observe(this) { ids ->
            imageAdapter.setFavorites(ids)
        }
    }
}
