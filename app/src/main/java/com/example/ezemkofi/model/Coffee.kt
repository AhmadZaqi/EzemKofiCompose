package com.example.ezemkofi.model

import androidx.annotation.DrawableRes

data class Coffee(
    val id: Int,
    val name: String,
    val category: CoffeeCategory,
    val rating: Double,
    val price: Double,
    val description: String,
    @DrawableRes val image: Int
)

