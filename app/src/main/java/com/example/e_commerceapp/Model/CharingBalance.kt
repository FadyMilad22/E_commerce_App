package com.example.e_commerceapp.Model

data class CharingBalance(
    val amount: Int,
    val cardNumber: String,
    val cvc: Int,
    val expMonth: Int,
    val expYear: Int
)