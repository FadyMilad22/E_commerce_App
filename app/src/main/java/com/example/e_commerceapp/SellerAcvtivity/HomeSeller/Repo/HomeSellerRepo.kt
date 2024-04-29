package com.example.e_commerceapp.SellerAcvtivity.HomeSeller.Repo

import com.example.e_commerceapp.Model.AddingItemResponse
import com.example.e_commerceapp.Model.Item
import com.example.e_commerceapp.Model.ItemsOfCategoryResponse
import retrofit2.Response


interface HomeSellerRepo {

    suspend fun getItemsofThisSeller(token: String): Response<ItemsOfCategoryResponse>

  }