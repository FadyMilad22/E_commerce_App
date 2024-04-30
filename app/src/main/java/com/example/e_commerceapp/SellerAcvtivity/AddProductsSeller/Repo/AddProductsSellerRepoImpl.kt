package com.example.e_commerceapp.SellerAcvtivity.AddProductsSeller.Repo

import com.example.e_commerceapp.Model.AddItem
import com.example.e_commerceapp.Model.AddingItemResponse
import com.example.e_commerceapp.Model.AuthResponse
import com.example.e_commerceapp.Model.Item
import com.example.e_commerceapp.Network.RemoteDataSource
import retrofit2.Response


class AddProductsSellerRepoImpl(val remoteDataSource: RemoteDataSource,
                 ): AddProductsSellerRepo {



    override suspend fun AddItem(item : AddItem, token : String): Response<AddingItemResponse> {
        return remoteDataSource.AddItem(item,token)
    }


    override suspend fun deleteItem(itemID : Int,token : String): Response<AuthResponse> {
        return remoteDataSource.deleteItem(itemID,token)
    }

    override suspend fun editItem(itemID : Int,token : String,item : AddItem): Response<AuthResponse> {
        return remoteDataSource.updateItem(itemID,token,item)
    }

}