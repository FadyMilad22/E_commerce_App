package com.example.e_commerceapp.HomeActivity.ShopScreen.Repo

import com.example.e_commerceapp.Model.AddItemToCartResponse
import com.example.e_commerceapp.Model.CartItem
import com.example.e_commerceapp.Model.CategoriesResponse
import com.example.e_commerceapp.Model.ItemsOfCategoryResponse
import com.example.e_commerceapp.Network.RemoteDataSource
import retrofit2.Response

class ShopRepoImpl(val remoteDataSource: RemoteDataSource,
                ): ShopRepo { // val localDataSource: LocalDataSource
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


//     override suspend fun getAllProducts () :Response<Recipe>{
//
//         return remoteDataSource.getAllMeals(' ');
//     }
override suspend fun getCategories(): Response<CategoriesResponse> {
    return remoteDataSource.getCategories()
}

    override suspend fun getItemByName(name :String): Response<ItemsOfCategoryResponse> {
        return remoteDataSource.getItemsByName(name)
    }
    override suspend fun AddItemToCart(item: CartItem, token :String): Response<AddItemToCartResponse> {
        return remoteDataSource.addItemtoCart(item,token)


    }

    override suspend fun getItemsofCategory(category : String): Response<ItemsOfCategoryResponse> {
        return remoteDataSource.getItemsOfCategory(category)
    }



                }