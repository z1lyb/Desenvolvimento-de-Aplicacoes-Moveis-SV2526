package com.example.catmap.network

import com.example.catmap.model.ImageItem
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Retrofit interface for The Cat API.
 * Full implementation will be added in Step 3.
 */
interface ApiService {

    /**
     * Fetches a list of cat images that include breed information.
     *
     * @param limit     Number of images to return (default 30).
     * @param hasBreeds Filter to only return images with breed data (1 = true).
     * @param apiKey    API key for authentication.
     */
    @GET("images/search")
    suspend fun getImages(
        @Query("limit") limit: Int = 30,
        @Query("has_breeds") hasBreeds: Int = 1,
        @Query("api_key") apiKey: String = RetrofitClient.API_KEY
    ): List<ImageItem>
}
