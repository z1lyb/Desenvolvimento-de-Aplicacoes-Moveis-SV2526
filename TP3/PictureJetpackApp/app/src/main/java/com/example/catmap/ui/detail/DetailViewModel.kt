package com.example.catmap.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.catmap.model.ImageItem

/**
 * ViewModel for [DetailActivity].
 * Holds the currently selected [ImageItem].
 * Will receive data via Intent extras in Step 6.
 */
class DetailViewModel : ViewModel() {

    private val _selectedImage = MutableLiveData<ImageItem?>()
    /** Observed by [DetailActivity] to populate the UI. */
    val selectedImage: LiveData<ImageItem?> = _selectedImage

    /** Sets the image to display on the detail screen. */
    fun setImage(image: ImageItem) {
        _selectedImage.value = image
    }
}
