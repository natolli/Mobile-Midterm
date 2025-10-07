package com.example.midterm.data.repository

import com.example.midterm.data.model.ImageItem

interface ImageRepository {
    fun getAllItems(): List<ImageItem>
    fun getNextItem(currentIndex: Int): ImageItem
}