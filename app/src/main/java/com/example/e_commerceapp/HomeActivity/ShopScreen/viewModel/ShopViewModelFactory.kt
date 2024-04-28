package com.example.e_commerceapp.HomeActivity.ShopScreen.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.e_commerceapp.HomeActivity.ShopScreen.Repo.ShopRepo



class ShopViewModelFactory(private val shopRepo: ShopRepo): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(ShopViewModel::class.java)) {
            ShopViewModel(shopRepo) as T
        }else{
            throw IllegalArgumentException("shopViewModel class not found")
        }
    }
}