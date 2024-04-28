package com.example.e_commerceapp.Authentication.Login.Repo

import com.example.e_commerceapp.Model.UserLoginResponse
import com.example.e_commerceapp.Network.RemoteDataSource
import retrofit2.Response

class LoginRepoImpl(val remoteDataSource: RemoteDataSource,
                ): LoginRepo { // val localDataSource: LocalDataSource




    override suspend fun loginUser(uid :String) :Response<UserLoginResponse> {
    return remoteDataSource.loginUser(uid) }
    }




