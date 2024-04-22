package com.example.recipemobileapp.Network


import androidx.lifecycle.MutableLiveData
import com.example.e_commerceapp.Model.Product
import com.example.e_commerceapp.Model.Products


object APIClient:RemoteDataSource {
    override suspend fun getAllProducts(): MutableLiveData<Products> {
        return APIHelper.retrofit.create(APIService::class.java).getAllProducts()
    }

    override suspend fun getRandomProduct(): Product {
        return APIHelper.retrofit.create(APIService::class.java).getRandomProduct()
    }

    override suspend fun getSearchResult(search: String): Product {
        return APIHelper.retrofit.create(APIService::class.java).getSearchResult() //search
    }


}