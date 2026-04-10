package com.example.catmap.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Singleton that provides the configured Retrofit instance and [ApiService].
 * Full configuration will be reviewed in Step 3.
 */
object RetrofitClient {

    /** API key for The Cat API. */
    const val API_KEY =
        "live_DtL9U3ATpDrpOWbBN8Avaw4khOIA9bR7UFuRib3l2DREmcfKLaS8yvPd5UYuUXhN"

    private const val BASE_URL = "https://api.thecatapi.com/v1/"

    /** Lazily-initialised Retrofit instance. */
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    /** Lazily-initialised [ApiService] implementation. */
    val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}
