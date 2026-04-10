package com.example.catmap.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.catmap.model.ImageItem

/**
 * ViewModel for [MainActivity].
 * Holds the list of images and loading/error state.
 * Connection to [com.example.catmap.repository.ImageRepository] will be
 * wired in Step 6.
 */
class MainViewModel : ViewModel() {

    private val _images = MutableLiveData<List<ImageItem>>(emptyList())
    /** Observed by [MainActivity] to update the RecyclerView. */
    val images: LiveData<List<ImageItem>> = _images

    private val _isLoading = MutableLiveData(false)
    /** Observed by [MainActivity] to show/hide the ProgressBar. */
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String?>(null)
    /** Observed by [MainActivity] to display error messages. */
    val errorMessage: LiveData<String?> = _errorMessage
}
