package com.example.e_commerceapp.Model

data class Order(
    val O_UserID: String,
    val Order_ID: Int,
    val Order_date: String,
    val Status: String,
    val Total_payment: Int
)