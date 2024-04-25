package com.example.e_commerceapp.HomeActivity.ShopScreen.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.e_commerceapp.HomeActivity.ShopScreen.Repo.ShopRepo
import kotlinx.coroutines.launch

class ShopViewModel(private val shopRepo: ShopRepo) : ViewModel() {



    fun getUserWithMeals(){
        viewModelScope.launch {
//            val response = shopRepo.getAllProducts()
//
//            Log.i("Fad123", response.code().toString())
//            Log.i("Fad123", "${response.isSuccessful}")
//            Log.i("Fad123", response.body().toString())



        }
    }

}