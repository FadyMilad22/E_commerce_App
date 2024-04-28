package com.example.e_commerceapp.Network

import com.example.e_commerceapp.Model.AddingItemResponse
import com.example.e_commerceapp.Model.AuthResponse
import com.example.e_commerceapp.Model.CategoriesResponse
import com.example.e_commerceapp.Model.ItemsOfCategoryResponse
import com.example.e_commerceapp.Model.Customer
import com.example.e_commerceapp.Model.Item
import com.example.e_commerceapp.Model.Seller
import com.example.e_commerceapp.Model.UserLoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header

import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path


interface APIService {




    // Customer functionality
    @POST("users/customers/register")
    suspend fun createCustomer(@Body user: Customer): Response<AuthResponse>





    // https://e-commerce-backend-atmh.onrender.com/

    // Items functionality
    @GET("items/categories/{category}")
    suspend fun getItemsInCategory(@Path("category") category: String) : Response<ItemsOfCategoryResponse>

    @GET("items/categories")
    suspend fun getCategories()  : Response<CategoriesResponse>


    @GET("items/{itemName}")
    suspend fun getItemByName(@Path("itemName") name : String) : Response<ItemsOfCategoryResponse>





//Seller Functionality

    @POST("users/sellers/register")
    suspend fun createSeller(@Body user: Seller): Response<AuthResponse>
    @POST("sellers/item")
    suspend fun addItem(@Body item: Item, @Header("Authorization") token: String): Response<AddingItemResponse>


    @DELETE("sellers/item/{Item_ID}")
    suspend fun deleteItem(@Path("Item_ID") itemId: Int , @Header("Authorization") token: String): Response<AuthResponse>



    @PUT("sellers/item/{Item_ID}")
    suspend fun editItem( @Path("Item_ID") itemId: Int, @Header("Authorization") token: String, @Body itemData: Item): Response<AuthResponse>


    @GET("sellers/item")
    suspend fun getItemsOfthisSeller( @Header("Authorization") token: String)  : Response<ItemsOfCategoryResponse>

// User functionality

    @POST("users/login")
    suspend fun userLogin(@Body uid: String): Response<UserLoginResponse>


}