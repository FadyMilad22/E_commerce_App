package com.example.e_commerceapp.SellerAcvtivity.AddProductsSeller.Repo

import com.example.e_commerceapp.Model.AddItem
import com.example.e_commerceapp.Model.AddingItemResponse
import com.example.e_commerceapp.Model.AuthResponse
import com.example.e_commerceapp.Model.Item
import retrofit2.Response


interface AddProductsSellerRepo {

    suspend fun editItem(itemID : Int,token : String,item : AddItem): Response<AuthResponse>
    suspend fun deleteItem(itemID : Int,token : String): Response<AuthResponse>

    suspend fun AddItem(item : AddItem, token : String): Response<AddingItemResponse>




}