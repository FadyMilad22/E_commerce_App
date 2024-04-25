package com.example.e_commerceapp.Network

import com.example.e_commerceapp.Model.AuthResponse
import com.example.e_commerceapp.Model.ItemsOfCategoryResponse
import com.example.e_commerceapp.Model.User
import retrofit2.Response


interface RemoteDataSource {

    suspend fun registerCustomer(user : User): Response<AuthResponse>
    suspend fun getItemsOfCategorie(category: String): Response<ItemsOfCategoryResponse>


}