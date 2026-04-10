package com.example.catmap.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Singleton that builds and exposes the configured [ApiService].
 *
 * Uses OkHttp with:
 *  - 30-second connect / read / write timeouts.
 *  - A [HttpLoggingInterceptor] (BODY level) for easier debugging during development.
 *
 * Use [apiService] as the entry point from [com.example.catmap.repository.ImageRepository].
 */
object RetrofitClient {

    /** The Cat API authentication key (from docs/07_api_usage.md). */
    const val API_KEY =
        "live_DtL9U3ATpDrpOWbBN8Avaw4khOIA9bR7UFuRib3l2DREmcfKLaS8yvPd5UYuUXhN"

    private const val BASE_URL = "https://api.thecatapi.com/v1/"

    private const val TIMEOUT_SECONDS = 30L

    /** OkHttp logging interceptor – logs full request/response bodies. */
    private val loggingInterceptor: HttpLoggingInterceptor by lazy {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    /** Configured OkHttp client with timeouts and logging. */
    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .build()
    }

    /** Retrofit instance backed by the configured OkHttp client and Gson converter. */
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    /** Ready-to-use [ApiService] implementation. */
    val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}
