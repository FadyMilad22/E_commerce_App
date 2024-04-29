package com.example.e_commerceapp.Model

data class Item(
    val Categories: String,
    val Description: String,
    val I_UserID: String?,
    val Item_ID: Int?,
    val Name: String,
    val Price: Double,
    val Quantity: Int,
    val URL: String
)

//{
//  "Name": "Samsung A13",
//  "Description": "An andriod phone",
//  "Price" : 5,
//  "Quantity": 5,
//  "URL" : "Dummy",
//  "categories": ["Electronics", "Home Accessories"]
//}