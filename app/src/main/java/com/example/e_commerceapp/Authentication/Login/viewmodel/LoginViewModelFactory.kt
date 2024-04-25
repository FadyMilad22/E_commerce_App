package com.example.e_commerceapp.Authentication.Login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.e_commerceapp.Authentication.Login.Repo.LoginRepo
import com.example.e_commerceapp.Authentication.SignUp.Repo.SignupRepo
import com.example.e_commerceapp.HomeActivity.ShopScreen.Repo.ShopRepo



class LoginViewModelFactory(private val loginRepo: LoginRepo): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            LoginViewModel(loginRepo) as T
        }else{
            throw IllegalArgumentException("Login ViewModel class not found")
        }
    }
}