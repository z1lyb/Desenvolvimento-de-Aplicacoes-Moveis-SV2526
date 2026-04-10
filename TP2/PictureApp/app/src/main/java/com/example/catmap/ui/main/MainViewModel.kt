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

        viewModelScope.launch {
            when (val result = repository.getImages()) {
                is Resource.Success -> {
                    _isLoading.value = false
                    _images.value = result.data
                }
                is Resource.Error -> {
                    _isLoading.value = false
                    _errorMessage.value = result.message
                }
                // ImageRepository doesn't currently return Resource.Loading, but it's handled here for completeness
                is Resource.Loading -> {
                    _isLoading.value = true
                }
            }
        }
    }
}
