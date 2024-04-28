package com.example.e_commerceapp.SellerAcvtivity.CRUDProductsSeller.Repo

import com.example.e_commerceapp.Model.AddingItemResponse
import com.example.e_commerceapp.Model.AuthResponse
import com.example.e_commerceapp.Model.Item
import com.example.e_commerceapp.Model.ItemsOfCategoryResponse
import com.example.e_commerceapp.Network.RemoteDataSource
import com.example.e_commerceapp.SellerAcvtivity.CRUDProductsSeller.Repo.CRUDProductsSellerRepo
import retrofit2.Response


class CRUDProductsSellerRepoImpl(val remoteDataSource: RemoteDataSource,
                 ): CRUDProductsSellerRepo {



    override suspend fun getItemsofThisSeller(token: String): Response<ItemsOfCategoryResponse> {
        return remoteDataSource.getItemsofthisSeller(token)
    }

    override suspend fun AddItem(item : Item,token : String): Response<AddingItemResponse> {
        return remoteDataSource.AddItem(item,token)
    }
    override suspend fun deleteItem(itemID : Int,token : String): Response<AuthResponse> {
        return remoteDataSource.deleteItem(itemID,token)
    }

    override suspend fun editItem(itemID : Int,token : String,item : Item): Response<AuthResponse> {
        return remoteDataSource.updateItem(itemID,token,item)
    }

}