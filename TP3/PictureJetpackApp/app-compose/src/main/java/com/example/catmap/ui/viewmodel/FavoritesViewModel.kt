package com.example.catmap.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.catmap.model.ImageItem
import com.example.catmap.repository.FavoritesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class FavoritesViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = FavoritesRepository(application)

    private val _favorites = MutableStateFlow<List<ImageItem>>(emptyList())
    val favorites: StateFlow<List<ImageItem>> = _favorites.asStateFlow()

    init {
        loadFavorites()
    }

    fun loadFavorites() {
        _favorites.value = repository.getFavorites()
    }

    fun toggleFavorite(item: ImageItem) {
        repository.toggleFavorite(item)
        loadFavorites()
    }
}
