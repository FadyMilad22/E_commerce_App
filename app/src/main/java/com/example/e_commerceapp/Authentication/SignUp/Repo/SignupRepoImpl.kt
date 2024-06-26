package com.example.e_commerceapp.Authentication.SignUp.Repo

import com.example.e_commerceapp.Model.AuthResponse
import com.example.e_commerceapp.Model.Customer
import com.example.e_commerceapp.Model.Seller

import com.example.e_commerceapp.Network.RemoteDataSource
import retrofit2.Response

class SignupRepoImpl(val remoteDataSource: RemoteDataSource,
                ): SignupRepo { // val localDataSource: LocalDataSource
    //    override suspend fun deleteMeal(meal: Meal) {
//        localDataSource.deleteMeal(meal)
//    }
//
//    override suspend fun getuserWithMeals(): List<Userwithmeals> {
//        return localDataSource.getuserWithMeals()
//    }
//
//    override suspend fun deleteWishlist(wishlist: Wishlist) {
//        localDataSource.deleteWishlist(wishlist)
//    }
    override suspend fun regestercustomer(user: Customer): Response<AuthResponse> {
        return remoteDataSource.registerCustomer(user)
    }

    override suspend fun regesterSeller(user: Seller): Response<AuthResponse> {
        return remoteDataSource.registerSeller(user)
    }

//     override suspend fun getAllProducts () :Response<Recipe>{
//
//         return remoteDataSource.getAllMeals(' ');
//     }
//

}
