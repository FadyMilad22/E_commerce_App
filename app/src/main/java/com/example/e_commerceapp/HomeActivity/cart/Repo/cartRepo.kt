package com.example.e_commerceapp.HomeActivity.History.Repo

import com.example.e_commerceapp.Model.AddItemToCartResponse
import com.example.e_commerceapp.Model.AuthResponse
import com.example.e_commerceapp.Model.Cart
import com.example.e_commerceapp.Model.CartItem
import retrofit2.Response


interface cartRepo {

    suspend fun getCart(token : String) : Response<Cart>
    suspend fun editquantity(cartItem: CartItem, token: String): Response<AddItemToCartResponse>

    suspend fun deleteitem(itemID : Int,token : String) : Response<AddItemToCartResponse>

    suspend fun confirmOrder(token: String): Response<AuthResponse>

}