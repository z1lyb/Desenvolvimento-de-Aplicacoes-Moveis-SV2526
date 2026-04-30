package com.example.catmap.repository

import com.example.catmap.model.ImageItem
import com.example.catmap.model.Resource
import com.example.catmap.network.RetrofitClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Single source of truth for image data.
 *
 * Wraps all network calls in a [Resource] so the ViewModel layer never needs to
 * handle raw exceptions. Two operations are exposed:
 *  - [getImages]     : fetch the main gallery list as a stream of progress/success.
 *  - [getImageById]  : fetch full details for a single image (detail screen).
 */
class ImageRepository {

    private val apiService = RetrofitClient.apiService

    /**
     * Fetches up to [totalLimit] cat images that have breed information attached.
     * Fetches in batches of 20 to provide loading progress updates.
     *
     * @param totalLimit Number of images to retrieve (default 100).
     * @return A [Flow] of [Resource] states (Loading, Success, or Error).
     */
    fun getImages(totalLimit: Int = 100): Flow<Resource<List<ImageItem>>> = flow {
        val batchSize = 20
        val batches = totalLimit / batchSize
        val allImages = mutableListOf<ImageItem>()

        try {
            for (i in 0 until batches) {
                // Emit progress (0%, 20%, 40%, 60%, 80%)
                emit(Resource.Loading(progress = (i * 100) / batches))

                val batchImages = apiService.searchImages(
                    apiKey = RetrofitClient.API_KEY,
                    limit = batchSize,
                    page = i,
                    hasBreeds = 1
                )
                allImages.addAll(batchImages)
            }
            // Emit final success state
            emit(Resource.Loading(progress = 100))
            emit(Resource.Success(allImages))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "An unexpected error occurred."))
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
