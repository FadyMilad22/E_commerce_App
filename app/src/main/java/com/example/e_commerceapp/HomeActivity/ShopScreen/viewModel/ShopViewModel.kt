package com.example.e_commerceapp.HomeActivity.ShopScreen.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.e_commerceapp.HomeActivity.ShopScreen.Repo.ShopRepo
import com.example.e_commerceapp.Model.Category
import com.example.e_commerceapp.Model.Item
import kotlinx.coroutines.launch

class ShopViewModel(private val shopRepo: ShopRepo) : ViewModel() {

    private val _categories = MutableLiveData<List<Category>>()
    val categories: LiveData<List<Category>> =  _categories

    private val _searchItems = MutableLiveData<List<Item>>()
    val searchItems: LiveData<List<Item>> =  _searchItems



    fun getCategories(){
        viewModelScope.launch {
            val response = shopRepo.getCategories()
            if(response.isSuccessful){
                Log.i("Fady1212","Message :${response.body()?.message} \n body : ${response.body()?.categories} \n Code: ${response.code()}")
                _categories.value=response.body()?.categories
//          //      _categories.value=  listOf(
//                    "Technology", "Science", "News", "Sports", "Entertainment",
//                    "Music", "Travel", "Food", "Fashion", "Business",
//                    "Health", "Lifestyle", "Education", "Politics", "Environment",
//                    "Gaming", "Art", "Design", "Photography", "Animals"
//                )
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






}