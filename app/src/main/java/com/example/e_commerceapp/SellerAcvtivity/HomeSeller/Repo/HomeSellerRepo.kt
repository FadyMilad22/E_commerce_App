package com.example.e_commerceapp.SellerAcvtivity.HomeSeller.Repo

import com.example.e_commerceapp.Model.AddingItemResponse
import com.example.e_commerceapp.Model.Item
import com.example.e_commerceapp.Model.ItemsOfCategoryResponse
import com.example.e_commerceapp.Model.SellerReportsResponse
import com.example.e_commerceapp.Model.UserDataResponse
import retrofit2.Response


interface HomeSellerRepo {

    suspend fun getItemsofThisSeller(token: String): Response<ItemsOfCategoryResponse>
    suspend fun getUserData(token: String): Response<UserDataResponse>
    suspend fun getReports(token: String): Response<SellerReportsResponse>

}