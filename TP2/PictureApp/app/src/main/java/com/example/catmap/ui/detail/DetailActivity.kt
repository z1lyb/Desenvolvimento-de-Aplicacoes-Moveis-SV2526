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

        val imageJson = intent.getStringExtra("EXTRA_IMAGE_JSON")
        if (imageJson != null) {
            val image = com.google.gson.Gson().fromJson(imageJson, com.example.catmap.model.ImageItem::class.java)
            viewModel.setImage(image)
        }

        // Observe selected image and populate the UI
        viewModel.selectedImage.observe(this) { image ->
            image ?: return@observe
            
            // Populate breed info fields
            binding.textBreedName.text = image.breed?.name ?: "Unknown Breed"
            binding.textOrigin.text = getString(com.example.catmap.R.string.label_origin, image.breed?.origin ?: "Unknown")
            binding.textLifeSpan.text = getString(com.example.catmap.R.string.label_life_span, image.breed?.lifeSpan ?: "?")
            binding.textWeight.text = getString(com.example.catmap.R.string.label_weight, image.breed?.weight?.takeIf { it.isNotBlank() } ?: "?")
            binding.textTemperament.text = getString(com.example.catmap.R.string.label_temperament, image.breed?.temperament ?: "Unknown")
            
            // Load large image with Glide
            com.bumptech.glide.Glide.with(this)
                .load(image.url)
                .placeholder(com.example.catmap.R.color.card_stroke)
                .error(android.R.drawable.ic_menu_gallery)
                .transition(com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade())
                .centerCrop()
                .into(binding.imageViewDetail)
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
