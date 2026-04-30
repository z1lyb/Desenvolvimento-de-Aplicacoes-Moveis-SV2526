package com.example.catmap.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.catmap.model.ImageItem
import com.example.catmap.model.Resource
import com.example.catmap.repository.FavoritesRepository
import com.example.catmap.repository.ImageRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ImageRepository()
    private val favoritesRepository = FavoritesRepository(application)

    private val _images = MutableStateFlow<List<ImageItem>>(emptyList())
    val images: StateFlow<List<ImageItem>> = _images.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _loadingProgress = MutableStateFlow(0)
    val loadingProgress: StateFlow<Int> = _loadingProgress.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    private val _favoriteIds = MutableStateFlow<Set<String>>(emptySet())
    val favoriteIds: StateFlow<Set<String>> = _favoriteIds.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    init {
        refreshFavorites()
        fetchImages()
    }

    fun onSearchQueryChange(query: String) {
        _searchQuery.value = query
    }

    fun toggleFavorite(item: ImageItem) {
        favoritesRepository.toggleFavorite(item)
        refreshFavorites()
    }

    fun refreshFavorites() {
        _favoriteIds.value = favoritesRepository.getFavorites().map { it.id }.toSet()
    }

    fun fetchImages() {
        _isLoading.value = true
        _errorMessage.value = null
        _loadingProgress.value = 0

        viewModelScope.launch {
            repository.getImages().collect { result ->
                when (result) {
                    is Resource.Success -> {
                        _isLoading.value = false
                        _images.value = result.data
                    }
                    is Resource.Error -> {
                        _isLoading.value = false
                        _errorMessage.value = result.message
                    }
                    is Resource.Loading -> {
                        _isLoading.value = true
                        _loadingProgress.value = result.progress
                    }
                }
            }
        }
    }
}
