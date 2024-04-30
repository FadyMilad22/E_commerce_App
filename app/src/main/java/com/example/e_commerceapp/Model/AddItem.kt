package com.example.e_commerceapp.Model

data class AddItem(
val categories: List<String>,
val Description: String,
val Name: String,
val Price: Double,
val Quantity: Int,
val URL: String
)
