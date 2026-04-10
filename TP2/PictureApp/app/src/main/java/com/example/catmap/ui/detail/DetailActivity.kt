package com.example.catmap.ui.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.catmap.databinding.ActivityDetailBinding

/**
 * Displays detailed information about a selected cat breed.
 *
 * Responsibilities:
 *  - Set up the Toolbar with back navigation.
 *  - Receive the selected [com.example.catmap.model.ImageItem] via Intent extras (Step 6).
 *  - Populate image, breed name, origin, life span, weight, temperament.
 *  - Open the breed's Wikipedia page on button click (Step 9).
 *
 * Full data binding will be added in Step 6.
 */
class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val viewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up Toolbar with back arrow
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Observe selected image – population logic added in Step 6
        viewModel.selectedImage.observe(this) { image ->
            image ?: return@observe
            binding.textBreedName.text = image.breed?.name ?: ""
            // Further field binding added in Step 6
        }

        // Wikipedia button – full implementation in Step 9
        binding.buttonWikipedia.setOnClickListener {
            val url = viewModel.selectedImage.value?.breed?.wikipediaUrl
            if (!url.isNullOrBlank()) {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}
