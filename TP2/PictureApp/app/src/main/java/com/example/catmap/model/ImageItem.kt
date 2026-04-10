package com.example.catmap.model

/**
 * Represents a single image item returned by The Cat API.
 * The [breed] convenience property returns the first breed entry (if any).
 */
data class ImageItem(
    val id: String = "",
    val url: String = "",
    val breeds: List<BreedInformation> = emptyList()
) {
    /** Convenience accessor for the primary breed (API may return multiple). */
    val breed: BreedInformation?
        get() = breeds.firstOrNull()
}
