package com.example.midterm.data.datasource

import com.example.midterm.R
import com.example.midterm.data.model.ImageItem

object StaticDataSource {
    val items = listOf(
        ImageItem(
            titleResId = R.string.compro_admission_team,
            imageResId = R.drawable.compro_admission_team
        ),
        ImageItem(
            titleResId = R.string.compro_professionals,
            imageResId = R.drawable.compro_professionals
        ),
        ImageItem(
            titleResId = R.string.faculty_student,
            imageResId = R.drawable.faculty_student
        ),
        ImageItem(
            titleResId = R.string.friends,
            imageResId = R.drawable.friends
        ),
        ImageItem(
            titleResId = R.string.graduation,
            imageResId = R.drawable.graduation
        ),
        ImageItem(
            titleResId = R.string.miu_campus,
            imageResId = R.drawable.miu_campus
        ),
        ImageItem(
            titleResId = R.string.miu_snow_fall,
            imageResId = R.drawable.miu_snow_fall
        ),
        ImageItem(
            titleResId = R.string.rainbow,
            imageResId = R.drawable.rainbow
        ),
        ImageItem(
            titleResId = R.string.sustainable_living_center,
            imageResId = R.drawable.sustainable_living_center
        )
    )
}
