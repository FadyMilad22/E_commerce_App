package com.example.recipemobileapp.Network

import androidx.lifecycle.MutableLiveData
import com.example.e_commerceapp.Model.Product
import com.example.e_commerceapp.Model.Products


interface RemoteDataSource {
    suspend fun getAllProducts():MutableLiveData<Products>
    suspend fun getRandomProduct(): Product
    suspend fun getSearchResult( search :String): Product



}