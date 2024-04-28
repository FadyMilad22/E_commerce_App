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


object APIClient: RemoteDataSource {


    override suspend fun registerCustomer(user: Customer): Response<AuthResponse> {
        return APIHelper.retrofit.create(APIService::class.java).createCustomer(user)
    }

    override suspend fun registerSeller(user: Seller): Response<AuthResponse> {
        return APIHelper.retrofit.create(APIService::class.java).createSeller(user)
    }

    override suspend fun getItemsOfCategory(category: String): Response<ItemsOfCategoryResponse> {
        return APIHelper.retrofit.create(APIService::class.java).getItemsInCategory(category)
    }

    override suspend fun getItemsByName(name: String): Response<ItemsOfCategoryResponse> {
        return APIHelper.retrofit.create(APIService::class.java).getItemByName(name)
    }

    override suspend fun getCategories(): Response<CategoriesResponse> {
        return APIHelper.retrofit.create(APIService::class.java).getCategories()
    }

    override suspend fun AddItem(item: Item, token: String): Response<AddingItemResponse> {
        return APIHelper.retrofit.create(APIService::class.java).addItem(item, token)

    }

    override suspend fun deleteItem(itemID: Int, token: String): Response<AuthResponse> {
        return APIHelper.retrofit.create(APIService::class.java).deleteItem(itemID, token)

    }

    override suspend fun updateItem(
        itemID: Int,
        token: String,
        item: Item
    ): Response<AuthResponse> {
        return APIHelper.retrofit.create(APIService::class.java).editItem(itemID, token, item)

    }

    override suspend fun getItemsofthisSeller(token: String): Response<ItemsOfCategoryResponse> {
        return APIHelper.retrofit.create(APIService::class.java).getItemsOfthisSeller(token)

    }

    override suspend fun loginUser(uid: String ): Response<UserLoginResponse> {
        return APIHelper.retrofit.create(APIService::class.java).userLogin(uid)
    }


}