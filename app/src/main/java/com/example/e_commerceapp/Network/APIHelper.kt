package com.example.recipemobileapp.Network

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIHelper {
    val gson = GsonBuilder().serializeNulls().create()
    val retrofit = Retrofit.Builder()
        .baseUrl("https://dummyjson.com/products")
        .addConverterFactory(GsonConverterFactory.create(gson)).build()
}