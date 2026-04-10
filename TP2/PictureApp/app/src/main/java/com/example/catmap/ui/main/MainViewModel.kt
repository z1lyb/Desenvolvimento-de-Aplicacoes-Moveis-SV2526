package com.example.catmap.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catmap.model.ImageItem
import com.example.catmap.model.Resource
import com.example.catmap.repository.ImageRepository
import kotlinx.coroutines.launch

/**
 * ViewModel for [MainActivity].
 * Holds the list of images and loading/error state.
 * Uses [ImageRepository] to fetch data from The Cat API.
 */
class MainViewModel : ViewModel() {

    private val repository = ImageRepository()

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

    init {
        // Fetch images initially when the ViewModel is created
        fetchImages()
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
