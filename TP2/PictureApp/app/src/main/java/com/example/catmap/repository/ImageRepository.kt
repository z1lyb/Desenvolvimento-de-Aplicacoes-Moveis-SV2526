package com.example.catmap.repository

import com.example.catmap.model.ImageItem
import com.example.catmap.network.RetrofitClient

/**
 * Single source of truth for image data.
 * Delegates network calls to [RetrofitClient.apiService].
 * Full error handling and caching will be added in Step 3.
 */
class ImageRepository {

    private val apiService = RetrofitClient.apiService

    /**
     * Fetches a list of cat images from The Cat API.
     * Must be called from a coroutine scope (suspend function).
     */
    suspend fun getImages(): List<ImageItem> {
        return apiService.getImages()
    }
}
