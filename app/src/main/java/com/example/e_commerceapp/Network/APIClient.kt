package com.example.e_commerceapp.Network


import com.example.e_commerceapp.Model.AuthResponse
import com.example.e_commerceapp.Model.ItemsOfCategoryResponse
import com.example.e_commerceapp.Model.User
import retrofit2.Response


object APIClient: RemoteDataSource {


    override suspend fun registerCustomer(user : User): Response<AuthResponse> {
        return APIHelper.retrofit.create(APIService::class.java).createCustomer(user)
    }
    override suspend fun getItemsOfCategorie(category: String): Response<ItemsOfCategoryResponse> {
        return APIHelper.retrofit.create(APIService::class.java).getItemsInCategory(category)
    }

}