package com.example.midterm.ui.viewmodel

import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import com.example.midterm.data.repository.ImageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

// The immutable UI state data class
data class ImageExplorerUiState(
    val currentImageResId: Int,
    @StringRes val currentTitleResId: Int,
    val isFirstLaunch: Boolean = true
)

@HiltViewModel
class ImageViewModel @Inject constructor(
    private val imageRepository: ImageRepository
) : ViewModel() {

    // Private mutable state flow
    private val _uiState = MutableStateFlow(
        // Initialize with the first item from the repository
        ImageExplorerUiState(
            currentImageResId = imageRepository.getAllItems().first().imageResId,
            currentTitleResId = imageRepository.getAllItems().first().titleResId
        )
    )

    // Public read-only state flow for the UI to consume
    val uiState: StateFlow<ImageExplorerUiState> = _uiState.asStateFlow()

    // Internal state to keep track of the current item's index
    private var currentIndex = 0

    // Public method to trigger the next item logic
    fun showNextImage() {
        val allItems = imageRepository.getAllItems()
        // Calculate the next index with wrap-around
        currentIndex = (currentIndex + 1) % allItems.size
        val nextItem = allItems[currentIndex]

        _uiState.update { currentState ->
            currentState.copy(
                currentImageResId = nextItem.imageResId,
                currentTitleResId = nextItem.titleResId,
                isFirstLaunch = false
            )
        }
    }
}
