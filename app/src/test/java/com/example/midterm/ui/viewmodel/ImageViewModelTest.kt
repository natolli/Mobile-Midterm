package com.example.midterm.ui.viewmodel

import com.example.midterm.data.model.ImageItem
import com.example.midterm.data.repository.ImageRepositoryImpl
import kotlinx.coroutines.Dispatchers import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class ImageViewModelTest {

    // 1. Mock the repository, which is a dependency of the ViewModel
    private val mockImageRepository: ImageRepositoryImpl = mock()

    // 2. The class we are going to test
    private lateinit var viewModel: ImageViewModel

    // 3. Test dispatcher for managing coroutines in tests
    private val testDispatcher = StandardTestDispatcher()

    // 4. Define fake data that our mock repository will return
    private val fakeImageItems = listOf(
        ImageItem(titleResId = 1, imageResId = 101), // First item
        ImageItem(titleResId = 2, imageResId = 102), // Second item
        ImageItem(titleResId = 3, imageResId = 103)  // Third item
    )

    @Before
    fun setUp() {
        // Set the main dispatcher to our test dispatcher before each test
        Dispatchers.setMain(testDispatcher)

        // Configure the mock repository to return our fake data
        whenever(mockImageRepository.getAllItems()).thenReturn(fakeImageItems)

        // Initialize the ViewModel with the mock repository
        viewModel = ImageViewModel(mockImageRepository)
    }

    @After
    fun tearDown() {
        // Reset the main dispatcher after each test to avoid side effects
        Dispatchers.resetMain()
    }

    @Test
    fun `showNextImage() updates uiState from first to second item`() = runTest {
        // --- ARRANGE ---
        // The `setUp` method already initializes the ViewModel.
        // The initial state should be the first item.
        val initialState = viewModel.uiState.first()
        assertEquals(101, initialState.currentImageResId)
        assertEquals(1, initialState.currentTitleResId)

        // --- ACT ---
        // Call the function we want to test
        viewModel.showNextImage()

        // --- ASSERT ---
        // Get the new state after the function call
        val newState = viewModel.uiState.first()

        // Verify that the UI state has been updated to the second item's data and isFirstLaunch is false
        assertEquals(102, newState.currentImageResId) // Check if image updated
        assertEquals(2, newState.currentTitleResId)     // Check if title updated
        assertEquals(false, newState.isFirstLaunch)      // Check if launch flag is updated
    }
}
