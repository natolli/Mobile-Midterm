package com.example.midterm.data.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class ImageItem(
    @StringRes val titleResId: Int,
    @DrawableRes val imageResId: Int
)
