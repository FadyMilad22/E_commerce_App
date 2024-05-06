package com.example.e_commerceapp.Model

data class User(
    val Balance: Int,
    val Items_sold: Int?,
    val Phone_no: Long?,
    val User_ID: String,
    val Username: String,
    val addresses: List<String>?,
    val cards: List<Int>?
)