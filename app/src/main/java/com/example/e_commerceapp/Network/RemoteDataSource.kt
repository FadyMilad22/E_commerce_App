package com.example.e_commerceapp.Network

import com.example.e_commerceapp.Model.AddItem
import com.example.e_commerceapp.Model.AddingItemResponse
import com.example.e_commerceapp.Model.AuthResponse
import com.example.e_commerceapp.Model.CategoriesResponse
import com.example.e_commerceapp.Model.ItemsOfCategoryResponse
import com.example.e_commerceapp.Model.Customer
import com.example.e_commerceapp.Model.Item
import com.example.e_commerceapp.Model.LoginRequest
import com.example.e_commerceapp.Model.Seller
import com.example.e_commerceapp.Model.UserLoginResponse
import retrofit2.Response


interface RemoteDataSource {

    suspend fun registerCustomer(user : Customer): Response<AuthResponse>
    suspend fun registerSeller(user : Seller): Response<AuthResponse>
    suspend fun getItemsOfCategory(category: String): Response<ItemsOfCategoryResponse>
    suspend fun getCategories(): Response<CategoriesResponse>
    suspend fun getItemsByName(name: String): Response<ItemsOfCategoryResponse>
    suspend fun AddItem(item : AddItem, token : String) : Response<AddingItemResponse>
    suspend fun deleteItem(itemID : Int, token : String) : Response<AuthResponse>
    suspend fun updateItem(itemID : Int, token : String , item : AddItem) : Response<AuthResponse>
    suspend fun getItemsofthisSeller(token: String): Response<ItemsOfCategoryResponse>
    suspend fun loginUser(loginResquest : LoginRequest): Response<UserLoginResponse>

}