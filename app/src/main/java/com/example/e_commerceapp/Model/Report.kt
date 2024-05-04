package com.example.e_commerceapp.Model

data class Report(
    val Item_ID: Int,
    val Name: String,
    val TotalProfit: Int,
    val TotalQuantity: Int,
    val URL: String,
    val prices: List<Price>
)