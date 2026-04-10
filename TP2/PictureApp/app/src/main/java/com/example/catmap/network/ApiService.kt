package com.example.catmap.network

import com.example.catmap.model.ImageItem
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Retrofit interface for The Cat API (https://api.thecatapi.com/v1/).
 *
 * Two endpoints are used by this app:
 *  1. [searchImages] – fetch a paginated list of images that have breed data.
 *  2. [getImageById] – fetch full details for a single image (used on the detail screen).
 *
 * All functions are `suspend` so they can be called from a coroutine scope.
 */
interface ApiService {

    /**
     * Fetches a list of cat images that include breed information.
     *
     * Maps to:
     *   GET /v1/images/search?api_key=…&limit=30&has_breeds=1
     *
     * @param apiKey    Authentication key for The Cat API.
     * @param limit     Maximum number of images to return (default 30).
     * @param hasBreeds Pass 1 to return only images that have breed data attached.
     * @return          A list of [ImageItem] objects, each containing a URL and breed info.
     */
    @GET("images/search")
    suspend fun searchImages(
        @Query("api_key") apiKey: String,
        @Query("limit") limit: Int = 100,
        @Query("has_breeds") hasBreeds: Int = 1
    ): List<ImageItem>

    /**
     * Fetches the full details for a single cat image by its unique ID.
     *
     * Maps to:
     *   GET /v1/images/{id}
     *
     * Example: https://api.thecatapi.com/v1/images/0XYvRd7oD
     *
     * @param imageId The unique identifier of the image (e.g. "0XYvRd7oD").
     * @param apiKey  Authentication key for The Cat API.
     * @return        A single [ImageItem] with full breed details.
     */
    @GET("images/{id}")
    suspend fun getImageById(
        @Path("id") imageId: String,
        @Query("api_key") apiKey: String
    ): ImageItem
}
