package com.example.e_commerceapp.HomeActivity.HomeScreen.Repo

import com.example.e_commerceapp.Model.ItemsOfCategoryResponse
import retrofit2.Response


interface HomeRepo {
//    suspend fun deleteMeal(meal: Meal)
//    suspend fun getuserWithMeals(): List<Userwithmeals>
//    suspend fun deleteWishlist(wishlist: Wishlist)

    suspend fun getItemsofCategory(category : String): Response<ItemsOfCategoryResponse>

}