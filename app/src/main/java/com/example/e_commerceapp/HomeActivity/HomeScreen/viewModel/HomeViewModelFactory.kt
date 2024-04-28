package com.example.e_commerceapp.HomeActivity.HomeScreen.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.e_commerceapp.HomeActivity.HomeScreen.Repo.HomeRepo
import com.example.e_commerceapp.HomeActivity.ShopScreen.Repo.ShopRepo



class HomeViewModelFactory(private val homeRepo: HomeRepo): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            HomeViewModel(homeRepo) as T
        }else{
            throw IllegalArgumentException("HomeViewModel class not found")
        }
    }
}