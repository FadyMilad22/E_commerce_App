package com.example.e_commerceapp.HomeActivity.ShopScreen.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.e_commerceapp.HomeActivity.ShopScreen.Repo.ShopRepo
import com.example.e_commerceapp.HomeActivity.ShopScreen.Repo.ShopRepoImpl
import com.example.e_commerceapp.Model.CartItem
import com.example.e_commerceapp.Model.Category
import com.example.e_commerceapp.Model.Item
import kotlinx.coroutines.launch

class ShopViewModel(private val shopRepo: ShopRepo) : ViewModel() {

    private val _categories = MutableLiveData<List<Category>>()
    val categories: LiveData<List<Category>> =  _categories

    private val _searchItems = MutableLiveData<List<Item>>()
    val searchItems: LiveData<List<Item>> =  _searchItems

    private val _categoryItems = MutableLiveData<List<Item>?>()
    val categoryItems: LiveData<List<Item>?> =  _categoryItems

    private val _sucssesfulAddingToCart = MutableLiveData<Boolean?>(null)
    val succesfulAdding: LiveData<Boolean?> =  _sucssesfulAddingToCart


    fun getCategories(){
        viewModelScope.launch {
            val response = shopRepo.getCategories()
            if(response.isSuccessful){
                Log.i("Fady1212","Message :${response.body()?.message} \n body : ${response.body()?.categories} \n Code: ${response.code()}")
                _categories.value=response.body()?.categories

            }
        }
    }




    fun getSearchResult(query: String ){
        viewModelScope.launch {

            val response = shopRepo.getItemByName(query)
            if(response.isSuccessful){
                Log.i("Fady1212","Message :${response.body()?.message} \n body : ${response.body()?.items} \n Code: ${response.code()}")

                _searchItems.value =response.body()?.items
            }


        }

    }


    fun returnFalse(){
        viewModelScope.launch(){
            _sucssesfulAddingToCart.value= false
        }
    }

    fun addItemToCart(item: CartItem, token :String ){
        viewModelScope.launch {

            val response = shopRepo.AddItemToCart(item, token)
            Log.i("Fady5122","Message :${response.body()?.message} \nCode: ${response.code()}")
            if(response.isSuccessful){

_sucssesfulAddingToCart.value = true

            }


        }

    }

    fun getthisCategoryItems(category: String) {
        viewModelScope.launch {
            val response = shopRepo.getItemsofCategory(category)
            if (response.isSuccessful) {

                _categoryItems.value = response.body()?.items
                Log.i("Fady1212","Message :${response.body()?.message} \n body :${response.body()?.items} \n Code: ${response.code()}")
            }
        }
    }


}