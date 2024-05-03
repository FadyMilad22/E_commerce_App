package com.example.e_commerceapp.Model

data class History(
    val items: List<ItemX>,
    val orderDate: String,
    val orderID: Int,
    val orderPrice: Int
)