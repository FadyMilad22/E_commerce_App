package com.example.e_commerceapp.Model

data class Cart(
    val items: List<Item>,
    val message: String,
    val order: Order
)