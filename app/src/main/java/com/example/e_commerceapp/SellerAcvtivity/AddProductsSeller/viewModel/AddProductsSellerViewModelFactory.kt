package com.example.e_commerceapp.SellerAcvtivity.AddProductsSeller.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.e_commerceapp.SellerAcvtivity.AddProductsSeller.Repo.AddProductsSellerRepo
import com.example.e_commerceapp.HomeActivity.cart.viewModel.CartViewModel


class AddProductsSellerViewModelFactory(private val addProductsSellerRepo: AddProductsSellerRepo): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(AddProductsSellerViewModel::class.java)) {
            AddProductsSellerViewModel(addProductsSellerRepo) as T
        }else{
            throw IllegalArgumentException("CRUDProductsSeller ViewModel class not found")
        }
    }
}