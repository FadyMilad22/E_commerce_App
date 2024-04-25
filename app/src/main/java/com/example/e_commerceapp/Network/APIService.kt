package com.example.e_commerceapp.Network

import com.example.e_commerceapp.Model.AuthResponse
import com.example.e_commerceapp.Model.ItemsOfCategoryResponse
import com.example.e_commerceapp.Model.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET

import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query


interface APIService {


// it can returns MutableLiveData
    @POST("users/customers/register")
    suspend fun createCustomer(@Body user: User): Response<AuthResponse>

    // https://e-commerce-backend-atmh.onrender.com/
    @GET("items/categories/{category}")
    suspend fun getItemsInCategory(@Path("category") category: String) : Response<ItemsOfCategoryResponse>


}