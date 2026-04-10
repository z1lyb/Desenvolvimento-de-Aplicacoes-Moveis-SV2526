package com.example.catmap.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.catmap.model.ImageItem
import com.example.catmap.model.Resource
import com.example.catmap.repository.FavoritesRepository
import com.example.catmap.repository.ImageRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * ViewModel for [MainActivity].
 * Holds the list of images, loading/error state, and favorite statuses.
 * Uses [ImageRepository] for network data and [FavoritesRepository] for persistence.
 */
class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ImageRepository()
    private val favoritesRepository = FavoritesRepository(application)

    private val _images = MutableLiveData<List<ImageItem>>(emptyList())
    /** Observed by [MainActivity] to update the RecyclerView. */
    val images: LiveData<List<ImageItem>> = _images

    private val _isLoading = MutableLiveData(false)
    /** Observed by [MainActivity] to show/hide the ProgressBar. */
    val isLoading: LiveData<Boolean> = _isLoading

    private val _loadingProgress = MutableLiveData(0)
    /** Observed by [MainActivity] to update current loading percentage. */
    val loadingProgress: LiveData<Int> = _loadingProgress

    private val _errorMessage = MutableLiveData<String?>(null)
    /** Observed by [MainActivity] to display error messages. */
    val errorMessage: LiveData<String?> = _errorMessage

    private val _favoriteIds = MutableLiveData<Set<String>>(emptySet())
    /** Observed by [MainActivity] to update heart icons in the grid. */
    val favoriteIds: LiveData<Set<String>> = _favoriteIds

    init {
        // Load favorites from local storage initially
        refreshFavorites()
        // Fetch images initially when the ViewModel is created
        fetchImages()
    }

    /**
     * Toggles the favorite status of an image and updates the UI state.
     */
    fun toggleFavorite(item: ImageItem) {
        favoritesRepository.toggleFavorite(item)
        refreshFavorites()
    }

    /**
     * Refreshes the local list of favorite IDs to sync with storage.
     */
    fun refreshFavorites() {
        _favoriteIds.value = favoritesRepository.getFavorites().map { it.id }.toSet()
    }

    /**
     * Fetches images from the repository and updates the LiveData states appropriately.
     */
    fun fetchImages() {
        _isLoading.value = true
        _errorMessage.value = null // Clear previous errors
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
