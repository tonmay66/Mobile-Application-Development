package com.example.e_commerceproductlistingapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    val id: Int,
    val name: String,
    val price: Double,
    val rating: Float,
    val category: String,
    val imageRes: Int,
    var inCart: Boolean = false
) : Parcelable
