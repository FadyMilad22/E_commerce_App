package com.example.e_commerceapp.HomeActivity.History.Repo

import com.example.e_commerceapp.Model.HistoryResponse
import com.example.e_commerceapp.Model.ItemsOfCategoryResponse
import com.example.e_commerceapp.Network.RemoteDataSource
import retrofit2.Response


class HistoryRepoImpl(val remoteDataSource: RemoteDataSource,
                 ): HistoryRepo {



    override suspend fun getHistory(token :String): Response<HistoryResponse> {
        return remoteDataSource.getHistory(token)
    }



}