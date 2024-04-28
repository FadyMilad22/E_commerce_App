package com.example.e_commerceapp.HomeActivity.HomeScreen.Repo

import com.example.e_commerceapp.Model.ItemsOfCategoryResponse
import com.example.e_commerceapp.Network.RemoteDataSource
import retrofit2.Response


class HomeRepoImpl(val remoteDataSource: RemoteDataSource,
                 ): HomeRepo {


    override suspend fun getItemsofCategory(category : String): Response<ItemsOfCategoryResponse> {
        return remoteDataSource.getItemsOfCategory(category)
    }

}