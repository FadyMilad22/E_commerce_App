package com.example.e_commerceapp.HomeActivity.ShopScreen.Repo

import com.example.e_commerceapp.Model.CategoriesResponse
import com.example.e_commerceapp.Model.ItemsOfCategoryResponse
import retrofit2.Response

interface ShopRepo {
//    suspend fun deleteMeal(meal: Meal)
//    suspend fun getuserWithMeals(): List<Userwithmeals>
//    suspend fun deleteWishlist(wishlist: Wishlist)
    suspend fun getCategories(): Response<CategoriesResponse>

    suspend fun getItemByName(name :String): Response<ItemsOfCategoryResponse>
//    suspend fun getAllProducts () : Response<Recipe>
}