package com.example.e_commerceapp.HomeActivity.HomeScreen.Repo

import com.example.e_commerceapp.Model.AddItemToCartResponse
import com.example.e_commerceapp.Model.CartItem
import com.example.e_commerceapp.Model.ItemsOfCategoryResponse
import com.example.e_commerceapp.Model.UserDataResponse
import retrofit2.Response


interface HomeRepo {
//    suspend fun deleteMeal(meal: Meal)
//    suspend fun getuserWithMeals(): List<Userwithmeals>
//    suspend fun deleteWishlist(wishlist: Wishlist)

    suspend fun getItemsofCategory(category : String): Response<ItemsOfCategoryResponse>


    suspend fun AddItemToCart(item: CartItem, token :String): Response<AddItemToCartResponse>



}