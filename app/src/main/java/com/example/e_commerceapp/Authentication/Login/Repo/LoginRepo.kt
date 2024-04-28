package com.example.e_commerceapp.Authentication.Login.Repo

import com.example.e_commerceapp.Model.LoginRequest
import com.example.e_commerceapp.Model.UserLoginResponse
import retrofit2.Response

interface LoginRepo {

    suspend fun loginUser(loginResquest : LoginRequest) : Response<UserLoginResponse>


}