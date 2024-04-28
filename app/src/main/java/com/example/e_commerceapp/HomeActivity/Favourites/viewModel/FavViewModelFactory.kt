package com.example.e_commerceapp.HomeActivity.Favourites.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.e_commerceapp.HomeActivity.Favourites.Repo.FavRepo
import com.example.e_commerceapp.HomeActivity.ShopScreen.Repo.ShopRepo



class FavViewModelFactory(private val favRepo: FavRepo): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(FavsViewModel::class.java)) {
            FavsViewModel(favRepo) as T
        }else{
            throw IllegalArgumentException("FavViewModel class not found")
        }
    }
}