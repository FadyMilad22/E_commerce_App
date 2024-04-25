package com.example.e_commerceapp.Authentication.SignUp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.e_commerceapp.Authentication.SignUp.Repo.SignupRepo
import com.example.e_commerceapp.HomeActivity.ShopScreen.Repo.ShopRepo



class SignupViewModelFactory(private val signupRepo: SignupRepo): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(SignupViewModel::class.java)) {
            SignupViewModel(signupRepo) as T
        }else{
            throw IllegalArgumentException("SignUpViewModel class not found")
        }
    }
}