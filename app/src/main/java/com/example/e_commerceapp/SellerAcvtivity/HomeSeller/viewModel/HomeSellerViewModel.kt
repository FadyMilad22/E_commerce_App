package com.example.e_commerceapp.HomeActivity.cart.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_commerceapp.Model.Item
import com.example.e_commerceapp.SellerAcvtivity.HomeSeller.Repo.HomeSellerRepo
import com.google.android.material.internal.ContextUtils.getActivity
import kotlinx.coroutines.launch

class HomeSellerViewModel (private val homeSellerRepo: HomeSellerRepo): ViewModel() {

    private var _token = MutableLiveData<String?>()
    val token : LiveData<String?> =_token

    private var _itemsOfthisSeller = MutableLiveData<List<Item>>()
    val itemsOfthisSeller : LiveData<List<Item>> =_itemsOfthisSeller

    fun setStringData(data: String) {
        _token.value = data
    }


    fun getItemsOfThisSeller(token :String){
        viewModelScope.launch {

            val response=  homeSellerRepo.getItemsofThisSeller(token.trim())
            Log.i("Fady122","Is Response token ? ${token} ")
            Log.i("Fady122","Is Response Successful ?${response.isSuccessful} ")
            Log.i("Fady122","Is Response code ?${response.code()} ")
            Log.i("Fady122","Is Response body ?${response.body()} ")

            if (response.isSuccessful){

                _itemsOfthisSeller.value = response.body()?.items
            }

        }

    }








}