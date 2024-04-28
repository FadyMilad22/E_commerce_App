package com.example.e_commerceapp.HomeActivity.cart.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.e_commerceapp.SellerAcvtivity.HomeSeller.Repo.HomeSellerRepo


class HomeSellerViewModelFactory(private val homeSellerRepo: HomeSellerRepo): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(HomeSellerViewModel::class.java)) {
            HomeSellerViewModel(homeSellerRepo) as T
        }else{
            throw IllegalArgumentException("HomeSeller ViewModel class not found")
        }
    }
}