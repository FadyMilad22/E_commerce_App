package com.example.e_commerceapp.HomeActivity.cart.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.e_commerceapp.HomeActivity.History.Repo.cartRepo


class CartViewModelFactory(private val cartRepo: cartRepo): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(CartViewModel::class.java)) {
            CartViewModel(cartRepo) as T
        }else{
            throw IllegalArgumentException("cart ViewModel class not found")
        }
    }
}