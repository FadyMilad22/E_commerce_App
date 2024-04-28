package com.example.e_commerceapp.Authentication.SignUp.Repo

import com.example.e_commerceapp.Model.AuthResponse
import com.example.e_commerceapp.Model.Customer
import com.example.e_commerceapp.Model.Seller
import retrofit2.Response

interface SignupRepo {

suspend fun regestercustomer(user: Customer): Response<AuthResponse>
suspend fun regesterSeller(user: Seller): Response<AuthResponse>

}