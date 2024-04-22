package com.example.recipemobileapp.Network

import androidx.lifecycle.MutableLiveData
import com.example.e_commerceapp.Model.Product
import com.example.e_commerceapp.Model.Products

import retrofit2.http.GET
import retrofit2.http.Query



interface APIService {
    @GET("/")
    suspend fun getAllProducts():  MutableLiveData<Products>
// it can returns MutableLiveData
    @GET("/api/json/v1/1/random.php")  //don't use it's wring and old
    suspend fun getRandomProduct(): Product

    // it can returns MutableLiveData
    @GET("/api/json/v1/1/search.php")   //don't use it's wring and old
    suspend fun getSearchResult(): Product //@Query("s") search :String



// it can returns MutableLiveData



}