package com.example.catmap.model

import com.google.gson.annotations.SerializedName

/**
 * Represents breed information returned by The Cat API.
 * Maps to the nested "breeds" array in the API response.
 */
data class BreedInformation(
    val id: String = "",
    val name: String = "",
    val origin: String = "",
    @SerializedName("life_span") val lifeSpan: String = "",
    val weight: Map<String, String> = emptyMap(),
    val temperament: String = "",
    @SerializedName("wikipedia_url") val wikipediaUrl: String = ""
)
