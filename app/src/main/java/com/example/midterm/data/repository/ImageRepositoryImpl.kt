package com.example.midterm.data.repository

import com.example.midterm.data.datasource.StaticDataSource
import com.example.midterm.data.model.ImageItem
import javax.inject.Inject

class ImageRepositoryImpl @Inject constructor() : ImageRepository {

    private val items = StaticDataSource.items

    override fun getAllItems(): List<ImageItem> {
        return items
    }

    override fun getNextItem(currentIndex: Int): ImageItem {
        // This logic handles getting the next item and wrapping around
        val nextIndex = (currentIndex + 1) % items.size
        return items[nextIndex]
    }
}
