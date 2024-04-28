package com.example.e_commerceapp.SellerAcvtivity.CRUDProductsSeller.Repo

import com.example.e_commerceapp.Model.AddingItemResponse
import com.example.e_commerceapp.Model.AuthResponse
import com.example.e_commerceapp.Model.Item
import com.example.e_commerceapp.Model.ItemsOfCategoryResponse
import retrofit2.Response


interface CRUDProductsSellerRepo {

    suspend fun editItem(itemID : Int,token : String,item : Item): Response<AuthResponse>
    suspend fun deleteItem(itemID : Int,token : String): Response<AuthResponse>
    suspend fun AddItem(item : Item,token : String): Response<AddingItemResponse>
    suspend fun getItemsofThisSeller(token: String): Response<ItemsOfCategoryResponse>


}