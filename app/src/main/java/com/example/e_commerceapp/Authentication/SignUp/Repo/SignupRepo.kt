package com.example.e_commerceapp.Authentication.SignUp.Repo

import com.example.e_commerceapp.Model.AuthResponse
import com.example.e_commerceapp.Model.User
import retrofit2.Response

interface SignupRepo {
//    suspend fun deleteMeal(meal: Meal)
//    suspend fun getuserWithMeals(): List<Userwithmeals>
//    suspend fun deleteWishlist(wishlist: Wishlist)
suspend fun regestercustomer(user: User): Response<AuthResponse>

//    suspend fun getAllProducts () : Response<Recipe>
}