package com.example.e_commerceapp.Authentication.Login.Repo

import com.example.e_commerceapp.Model.UserLoginResponse
import retrofit2.Response

interface LoginRepo {

    suspend fun loginUser(uid :String) : Response<UserLoginResponse>


}