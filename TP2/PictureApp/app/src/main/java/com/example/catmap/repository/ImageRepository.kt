package com.example.catmap.repository

import com.example.catmap.model.ImageItem
import com.example.catmap.model.Resource
import com.example.catmap.network.RetrofitClient

/**
 * Single source of truth for image data.
 *
 * Wraps all network calls in a [Resource] so the ViewModel layer never needs to
 * handle raw exceptions. Two operations are exposed:
 *  - [getImages]     : fetch the main gallery list.
 *  - [getImageById]  : fetch full details for a single image (detail screen).
 */
class ImageRepository {

    private val apiService = RetrofitClient.apiService

    /**
     * Fetches up to [limit] cat images that have breed information attached.
     *
     * @param limit Number of images to retrieve (default 30).
     * @return [Resource.Success] with a list of [ImageItem], or [Resource.Error] on failure.
     */
    suspend fun getImages(limit: Int = 30): Resource<List<ImageItem>> {
        return try {
            val images = apiService.searchImages(
                apiKey = RetrofitClient.API_KEY,
                limit = limit,
                hasBreeds = 1
            )
            Resource.Success(images)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "An unexpected error occurred.")
        }
    }

    /**
     * Fetches full details for a single cat image by its unique ID.
     *
     * @param imageId The unique identifier of the image (e.g. "0XYvRd7oD").
     * @return [Resource.Success] with the [ImageItem], or [Resource.Error] on failure.
     */
    suspend fun getImageById(imageId: String): Resource<ImageItem> {
        return try {
            val image = apiService.getImageById(
                imageId = imageId,
                apiKey = RetrofitClient.API_KEY
            )
            Resource.Success(image)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "An unexpected error occurred.")
        }
    }
}
