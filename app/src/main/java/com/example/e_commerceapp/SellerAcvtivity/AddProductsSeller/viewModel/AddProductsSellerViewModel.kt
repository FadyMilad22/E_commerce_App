package com.example.e_commerceapp.SellerAcvtivity.AddProductsSeller.viewModel

import android.util.Log
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.Navigation
import com.example.e_commerceapp.Model.AddItem
import com.example.e_commerceapp.Model.AddingItemResponse
import com.example.e_commerceapp.Model.Item
import com.example.e_commerceapp.SellerAcvtivity.AddProductsSeller.Repo.AddProductsSellerRepo
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.launch
import retrofit2.Response

class AddProductsSellerViewModel (private val addProductsSellerRepo: AddProductsSellerRepo): ViewModel() {

    private var _successfullAdding = MutableLiveData<Boolean?>(null)
    val successfullAdding : LiveData<Boolean?> =_successfullAdding



    private var _successfullDelete = MutableLiveData<Boolean?>(null)
    val successfullDelete : LiveData<Boolean?> =_successfullDelete



    fun addProduct(item : AddItem, token : String) {

        viewModelScope.launch {

            val response = addProductsSellerRepo.AddItem(item, token)
            Log.i("Fady13", "The Adding response  : ${response.isSuccessful} ")
            Log.i("Fady13", "The Adding response  : ${response.code()} ")
            if (response.isSuccessful) {

                _successfullAdding.value = true

            }

        }

    }

    fun deleteProduct(itemID : Int ,token :String ){

        viewModelScope.launch {
            val response = addProductsSellerRepo.deleteItem(itemID, token)
            Log.i("Fady13","The Delete response  : ${response.isSuccessful} ")
            Log.i("Fady13","The Delete response  : ${response.code()} ")
            if (response.isSuccessful){

                _successfullDelete.value = true
            }
        }





    }




}