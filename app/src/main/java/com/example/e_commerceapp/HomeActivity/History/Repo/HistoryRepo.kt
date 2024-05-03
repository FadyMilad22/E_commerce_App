package com.example.e_commerceapp.HomeActivity.History.Repo

import com.example.e_commerceapp.Model.HistoryResponse
import retrofit2.Response


interface HistoryRepo {


    suspend fun getHistory(token :String): Response<HistoryResponse>
}