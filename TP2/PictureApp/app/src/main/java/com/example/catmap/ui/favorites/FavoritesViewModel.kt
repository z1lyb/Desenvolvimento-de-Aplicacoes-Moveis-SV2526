package com.example.catmap.ui.favorites

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.catmap.model.ImageItem
import com.example.catmap.repository.FavoritesRepository

/**
 * ViewModel for [FavoritesActivity].
 * Manages the display and toggling of favorite images.
 */
class FavoritesViewModel(application: Application) : AndroidViewModel(application) {

    private val favoritesRepository = FavoritesRepository(application)

    private val _favorites = MutableLiveData<List<ImageItem>>(emptyList())
    /** List of favorited images to display. */
    val favorites: LiveData<List<ImageItem>> = _favorites

    private val _favoriteIds = MutableLiveData<Set<String>>(emptySet())
    /** IDs of favorites for updating heart icon states. */
    val favoriteIds: LiveData<Set<String>> = _favoriteIds

    init {
        loadFavorites()
    }

    /**
     * Toggles favorite status. Since we are on the Favorites screen,
     * toggling off will remove the item from the list.
     */
    fun toggleFavorite(item: ImageItem) {
        favoritesRepository.toggleFavorite(item)
        loadFavorites()
    }

    /**
     * Reloads favorites from persistent storage.
     */
    fun loadFavorites() {
        val currentFavorites = favoritesRepository.getFavorites()
        _favorites.value = currentFavorites
        _favoriteIds.value = currentFavorites.map { it.id }.toSet()
    }
}
