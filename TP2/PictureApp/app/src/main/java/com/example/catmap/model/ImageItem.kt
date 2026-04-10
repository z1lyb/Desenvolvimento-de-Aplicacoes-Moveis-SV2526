package com.example.catmap.model

import com.google.gson.annotations.SerializedName

/**
 * Domain model for a single cat image returned by The Cat API.
 *
 * Fields match the data model defined in docs/04_data_model.md:
 *   - id    : unique image identifier
 *   - title : breed name used as a display title (derived from [breed])
 *   - url   : direct URL of the image
 *   - breed : primary breed information (API may return multiple; we use the first)
 *
 * API note: the JSON key is "breeds" (array). Gson deserialises it into [breedList];
 * the public [breed] and [title] properties expose the first entry for convenience.
 */
data class ImageItem(
    val id: String = "",
    val url: String = "",
    /** Backing list mapped from the API's "breeds" JSON array. */
    @SerializedName("breeds")
    private val breedList: List<BreedInformation> = emptyList()
) {
    /**
     * Display title derived from the primary breed name.
     * Matches the `title: String` field in the data model.
     */
    val title: String
        get() = breedList.firstOrNull()?.name ?: ""

    /**
     * Primary breed for this image.
     * Matches the `breed: BreedInformation` field in the data model.
     */
    val breed: BreedInformation?
        get() = breedList.firstOrNull()
}
