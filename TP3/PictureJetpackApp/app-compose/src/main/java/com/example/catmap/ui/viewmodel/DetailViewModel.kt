package com.example.catmap.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.catmap.model.ImageItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class DetailViewModel : ViewModel() {
    private val _selectedImage = MutableStateFlow<ImageItem?>(null)
    val selectedImage: StateFlow<ImageItem?> = _selectedImage.asStateFlow()

    fun setImage(image: ImageItem) {
        _selectedImage.value = image
    }
}
