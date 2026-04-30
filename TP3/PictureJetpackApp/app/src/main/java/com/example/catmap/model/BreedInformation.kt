package com.example.catmap.model

import com.google.gson.annotations.SerializedName

/**
 * Domain model for breed information nested inside a cat image response.
 *
 * Fields match the data model defined in docs/04_data_model.md:
 *   - id           : breed identifier (e.g. "abys")
 *   - name         : breed display name (e.g. "Abyssinian")
 *   - origin       : country of origin (e.g. "Egypt")
 *   - life_span    : typical lifespan in years (e.g. "14 - 15")
 *   - weight       : typical weight as a human-readable string (e.g. "3 - 5 kg")
 *   - temperament  : comma-separated personality traits
 *   - wikipedia_url: URL to the breed's Wikipedia article
 *
 * API note: the JSON "weight" field is an object with "imperial" and "metric" keys.
 * It is deserialized into the [weightData] backing property; the public [weight]
 * property extracts the metric value to match the `weight: String` in the data model.
 */
data class BreedInformation(
    val id: String = "",
    val name: String = "",
    val origin: String = "",

    /** Mapped from the API's snake_case "life_span" key. */
    @SerializedName("life_span")
    val lifeSpan: String = "",

    /**
     * Backing property for the API weight object, e.g. {"imperial":"7 - 10","metric":"3 - 5"}.
     * Use the public [weight] property for display.
     */
    @SerializedName("weight")
    private val weightData: Weight? = null,

    val temperament: String = "",

    /** Mapped from the API's snake_case "wikipedia_url" key. */
    @SerializedName("wikipedia_url")
    val wikipediaUrl: String = ""
) {
    /**
     * Human-readable weight string using the metric value.
     * Matches the `weight: String` field in the data model.
     * Example: "3 - 5 kg"
     */
    val weight: String
        get() = weightData?.metric?.let { "$it kg" } ?: ""
}

/**
 * Helper class for deserializing the nested weight object from the API.
 * Not part of the domain model — used only as a Gson mapping target.
 */
data class Weight(
    val imperial: String = "",
    val metric: String = ""
)
