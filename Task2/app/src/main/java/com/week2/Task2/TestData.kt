package com.week2.Task2

import androidx.annotation.DrawableRes

data class TestData(
    val text: String,
    @DrawableRes
    val img: Int,
    val checked: Boolean
)
