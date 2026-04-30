package com.example.catmap.repository

import android.content.Context
import android.content.SharedPreferences
import com.example.catmap.model.ImageItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Manages the persistence of favorite images using SharedPreferences.
 * Implements a FIFO (First-In-First-Out) policy limited to 5 items.
 */
class FavoritesRepository(context: Context) {

    private val prefs: SharedPreferences = context.getSharedPreferences("catmap_favorites", Context.MODE_PRIVATE)
    private val gson = Gson()

    /**
     * Retrieves the current list of favorite [ImageItem]s.
     * @return List of favorite images, ordered from oldest to newest.
     */
    fun getFavorites(): List<ImageItem> {
        val json = prefs.getString(KEY_FAVORITES, null) ?: return emptyList()
        return try {
            val type = object : TypeToken<List<ImageItem>>() {}.type
            gson.fromJson(json, type)
        } catch (e: Exception) {
            emptyList()
        }
    }

    /**
     * Toggles the favorite status of an [ImageItem].
     * If adding and the list has 5 items, the oldest one is removed (FIFO).
     *
     * @param item The image to add or remove.
     * @return The updated list of favorites.
     */
    fun toggleFavorite(item: ImageItem): List<ImageItem> {
        val current = getFavorites().toMutableList()
        val index = current.indexOfFirst { it.id == item.id }

        if (index != -1) {
            // Remove from favorites
            current.removeAt(index)
        } else {
            // Add to favorites with FIFO logic (max 5)
            if (current.size >= 5) {
                current.removeAt(0) // Remove oldest
            }
            current.add(item) // Add newest
        }

        saveFavorites(current)
        return current
    }

    /**
     * Checks if a specific image ID is in the favorites list.
     */
    fun isFavorite(itemId: String): Boolean {
        return getFavorites().any { it.id == itemId }
    }

    private fun saveFavorites(favorites: List<ImageItem>) {
        val json = gson.toJson(favorites)
        prefs.edit().putString(KEY_FAVORITES, json).apply()
    }

    companion object {
        private const val KEY_FAVORITES = "favorites_list"
    }
}
