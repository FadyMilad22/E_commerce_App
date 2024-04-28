package com.example.e_commerceapp.SellerAcvtivity.CRUDProductsSeller.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.e_commerceapp.SellerAcvtivity.CRUDProductsSeller.Repo.CRUDProductsSellerRepo
import com.example.e_commerceapp.HomeActivity.cart.viewModel.CartViewModel


class CRUDProductsSellerViewModelFactory(private val crudProductsSellerRepo: CRUDProductsSellerRepo): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(CartViewModel::class.java)) {
            CRUDProductsSellerViewModel(crudProductsSellerRepo) as T
        }else{
            throw IllegalArgumentException("CRUDProductsSeller ViewModel class not found")
        }
    }
}