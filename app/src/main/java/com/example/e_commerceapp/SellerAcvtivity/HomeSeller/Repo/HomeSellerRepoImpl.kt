package com.example.e_commerceapp.SellerAcvtivity.HomeSeller.Repo


import com.example.e_commerceapp.Model.AddingItemResponse
import com.example.e_commerceapp.Model.Item
import com.example.e_commerceapp.Model.ItemsOfCategoryResponse
import com.example.e_commerceapp.Network.RemoteDataSource
import retrofit2.Response


class HomeSellerRepoImpl(val remoteDataSource: RemoteDataSource,
                 ): HomeSellerRepo {


    override suspend fun getItemsofThisSeller(token: String): Response<ItemsOfCategoryResponse> {
        return remoteDataSource.getItemsofthisSeller(token)
    }





}